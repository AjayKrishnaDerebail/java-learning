package com.springbatch.config;

import com.springbatch.mapper.ProductRowMapper;
import com.springbatch.model.Product;
import com.springbatch.reader.ProductNameItemReader;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
  private JobBuilderFactory jobBuilderFactory;

  private StepBuilderFactory stepBuilderFactory;

  private DataSource datasource;

  @Autowired
  public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Autowired
  public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
  }
  @Autowired
  public void setDatasource(DataSource datasource) {
    this.datasource = datasource;
  }

  @Bean
  public ItemReader<String> itemReader() {
    List<String> productList = new ArrayList<>();
    productList.add("Product 1");
    productList.add("Product 2");
    productList.add("Product 3");
    productList.add("Product 4");
    productList.add("Product 5");
    productList.add("Product 6");
    productList.add("Product 7");
    productList.add("Product 8");

    return new ProductNameItemReader(productList);
  }

  /**
   * Creates and configures an ItemReader that reads Product data from a CSV file.
   * 
   * <p>This reader is responsible for:
   * <ul>
   *   <li>Reading data from 'data/ecommerceDevices.csv'</li>
   *   <li>Skipping the header row (line 1)</li>
   *   <li>Parsing each line into a Product object</li>
   *   <li>Handling CSV format with comma delimiters</li>
   * </ul>
   * 
   * @return Configured ItemReader that produces Product objects
   * 
   * @see Product
   * @see FlatFileItemReader
   * @see DefaultLineMapper
   */
  @Bean
  public ItemReader<Product> flatFileItemReader() {
    // Create a reader for Product objects
    FlatFileItemReader<Product> itemReader = new FlatFileItemReader<>();
    
    // Configure the CSV file location and skip the header row
    itemReader.setLinesToSkip(1);  // Skip header row
    itemReader.setResource(new ClassPathResource("data/ecommerceDevices.csv"));

    // Create a line mapper to convert each line into a Product object
    DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
    
    // Configure the tokenizer to parse CSV lines
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    
    // Map CSV columns to fields (must match CSV header exactly)
    lineTokenizer.setNames("product_id", "product_name", "product_category", "product_price");
    lineTokenizer.setDelimiter(",");  // Set CSV delimiter
    lineTokenizer.setStrict(false);     // Be lenient with line parsing

    // Set the tokenizer to the line mapper
    lineMapper.setLineTokenizer(lineTokenizer);
    
    // Configure how to map tokenized fields to a Product object
    lineMapper.setFieldSetMapper(
        fieldSet -> {
          // Create a new Product instance for each line
          Product product = new Product();
          
          // Map each CSV column to the corresponding Product field
          // Note: Field names must match those in the CSV header
          product.setProductId(fieldSet.readLong("product_id"));
          product.setProductName(fieldSet.readString("product_name"));
          product.setProductCategory(fieldSet.readString("product_category"));
          product.setProductPrice(fieldSet.readDouble("product_price"));
          
          return product;
        });

    // Set the configured line mapper to the reader
    itemReader.setLineMapper(lineMapper);
    
    return itemReader;
  }

  /**
   * Creates and configures a JdbcCursorItemReader to read Product data from the database.
   * The reader fetches product records ordered by product_id and maps them to Product objects
   * using a ProductRowMapper.
   *
   * @return Configured ItemReader instance for Product objects
   * @see Product
   * @see ProductRowMapper
   */
  @Bean
  public ItemReader<Product> jdbcCursorItemReader() {
    // Create a new JDBC cursor reader for a Product type
    JdbcCursorItemReader<Product> itemReader = new JdbcCursorItemReader<>();
    
    // Set the data source for database connection
    itemReader.setDataSource(datasource);
    
    // Define the SQL query to fetch products, ordered by product_id
    String sql = "SELECT product_id, product_name, product_category, product_price " +
                "FROM products ORDER BY product_id";
    itemReader.setSql(sql);
    
    // Configure the row mapper to convert ResultSet rows into Product objects
    itemReader.setRowMapper(new ProductRowMapper());
    
    return itemReader;
  }

  /**
   * Creates and configures a paging-based ItemReader for Product entities. This reader fetches data
   * in pages (chunks) from the database, making it memory-efficient for large datasets by loading
   * only a subset of records at a time.
   *
   * @return Configured ItemReader that reads Product entities using paging
   * @throws Exception if there's an error creating the query provider
   * @see Product
   * @see ProductRowMapper
   * @see JdbcPagingItemReader
   */
  @Bean
  public ItemReader<Product> jdbcPagingItemItemReader() throws Exception {
    // Initialize the paging item reader
    JdbcPagingItemReader<Product> itemReader = new JdbcPagingItemReader<>();

    // Set the data source for database connection
    itemReader.setDataSource(datasource);

    // Create and configure the query provider factory
    SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean =
        getSqlPagingQueryProviderFactoryBean();

    // Configure the reader with the query provider
    itemReader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());

    // Set the row mapper to convert ResultSet rows into Product objects
    itemReader.setRowMapper(new ProductRowMapper());

    // Set the page size (number of records per page)
    itemReader.setPageSize(2);

    return itemReader;
  }

  private SqlPagingQueryProviderFactoryBean getSqlPagingQueryProviderFactoryBean() {
    SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean =
        new SqlPagingQueryProviderFactoryBean();

    // Configure the query provider with data source and SQL components
    sqlPagingQueryProviderFactoryBean.setDataSource(datasource);

    // Define the SELECT clause with required columns
    sqlPagingQueryProviderFactoryBean.setSelectClause(
        "SELECT product_id, product_name, product_category, product_price");

    // Define the FROM clause with the table name
    sqlPagingQueryProviderFactoryBean.setFromClause("FROM products");

    // Set the sort key for consistent paging (required for paging to work correctly)
    sqlPagingQueryProviderFactoryBean.setSortKey("product_id");
    return sqlPagingQueryProviderFactoryBean;
  }

  @Bean
  public FlatFileItemWriter<Product> itemWriter() {
    FlatFileItemWriter<Product> itemWriter = new FlatFileItemWriter<>();
    itemWriter.setResource(new FileSystemResource("src/main/resources/data/ecommerceDevicesOutput.csv"));
    DelimitedLineAggregator<Product> lineAggregator = new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(",");
    BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[] { "productId", "productName", "productCategory", "productPrice" });
    lineAggregator.setFieldExtractor(fieldExtractor);
    itemWriter.setLineAggregator(lineAggregator);
    return itemWriter;
  }

  @Bean
  public Step firstStep() {
    return this.stepBuilderFactory
        .get("chunkBasedFirstStep")
        .<String, String>chunk(2)
        .reader(itemReader())
        .writer(
            new ItemWriter<>() {
              @Override
              public void write(List<? extends String> items) {
                System.out.println("Chunk processing started");
                items.forEach(System.out::println);
                System.out.println("Chunk processing ended");
              }
            })
        .build();
  }

  @Bean
  public Step secondStep() {
    return this.stepBuilderFactory
        .get("chunkBasedSecondStep")
        .<Product, Product>chunk(2)
        .reader(flatFileItemReader())
        .writer(
            (items) -> {
              System.out.println("Chunk processing started");
              items.forEach(System.out::println);
              System.out.println("Chunk processing ended");
            })
        .build();
  }

  @Bean
  public Step thirdStep() {
    return this.stepBuilderFactory
        .get("chunkBasedSecondStep")
        .<Product, Product>chunk(2)
        .reader(jdbcCursorItemReader())
        .writer(
            (items) -> {
              System.out.println("Chunk processing started");
              items.forEach(System.out::println);
              System.out.println("Chunk processing ended");
            })
        .build();
  }

  @Bean
  public Step fourthStep() throws Exception {
    return this.stepBuilderFactory
        .get("chunkBasedFourthStep")
        .<Product, Product>chunk(2)
        .reader(jdbcPagingItemItemReader())
        .writer(itemWriter()) // ✅ Let Spring Batch manage opening/writing/closing
        .build();
  }


  @Bean
  public Job firstJob() throws Exception {
    return this.jobBuilderFactory
        .get("firstJob")
        // .start(firstStep())
        //.start(secondStep())
        //.start(thirdStep())
        .start(fourthStep())
        .build();
  }
}
