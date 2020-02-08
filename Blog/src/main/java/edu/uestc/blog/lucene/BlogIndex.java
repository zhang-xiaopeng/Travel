package edu.uestc.blog.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import edu.uestc.blog.entity.Blog;
import edu.uestc.blog.utils.StringUtil;

/**
 * 使用Lucene对博客进行增删改查
 * @author 张霄鹏
 *
 */
public class BlogIndex {
	//文件存放目录
	private Directory dir = null ;
	//文件存放路径
	private String lucenePath = "C://lucene" ;
	
	/**
	 * 获取对Lucene的写入方法,被内部使用
	 * @throws IOException 
	 */
	private IndexWriter getWriter() throws IOException {
		//1.为文件目录赋值
		dir = FSDirectory.open(Paths.get(lucenePath, new String[0])) ;
		
		//2.实例化索引书写器并返回
/*		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer() ;//中文分析器
		IndexWriterConfig config = new IndexWriterConfig(analyzer) ;
		IndexWriter writer = new IndexWriter(dir,config) ;*/
		
		IndexWriter writer = new IndexWriter(dir,new IndexWriterConfig(new SmartChineseAnalyzer())) ;
		
		return writer ;
	}
	
	/**
	 * 增加索引
	 * @throws IOException 
	 */
	public void addIndex(Blog blog) throws IOException {
		
		//1.创建文件
		Document doc = new Document() ;
		//2.为文件赋值
		//三个参数（1）字段名，（2）字段值（3）是否存储
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()),Field.Store.YES));
		doc.add(new TextField("content",blog.getContent(),Field.Store.YES));
		doc.add(new TextField("keyword",blog.getKeyword(),Field.Store.YES));
		doc.add(new TextField("summary",blog.getSummary(),Field.Store.YES));
		
		//3.创建索引写入器
		IndexWriter writer = getWriter() ;
		//4.将文件写入索引写入器
		writer.addDocument(doc) ;
		//5.关闭写入器
		writer.close();
		
	}
	
	/**
	 * 更新索引
	 */
	public void updateIndex(Blog blog) throws IOException{
		//1.创建文件
		Document doc = new Document() ;
		//2.为文件赋值
		//三个参数（1）字段名，（2）字段值（3）是否存储
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()),Field.Store.YES));
		doc.add(new TextField("content",blog.getContent(),Field.Store.YES));
		doc.add(new TextField("keyword",blog.getKeyword(),Field.Store.YES));
		doc.add(new TextField("summary",blog.getSummary(),Field.Store.YES));
		
		//3.创建索引写入器
		IndexWriter writer = getWriter() ;
		//4.将文件写入索引写入器：两个参数，第一个参数用于确定主键
		writer.updateDocument(new Term("id",String.valueOf(blog.getId())),doc) ;
		//5.关闭写入器
		writer.close();
	}
	
	/**
	 * 删除索引
	 * @throws IOException 
	 */
	public void deleteIndex(String blogId) throws IOException {
		//1.创建索引写入器
		IndexWriter writer = getWriter() ;
		//2.将删除文件
		writer.deleteDocuments(new Term[] {new Term("id",blogId)}) ;
		//3.执行删除
		writer.forceMergeDeletes(); 
		//4.提交
		writer.commit();
		//5.关闭
		writer.close();
	}
	
	/**
	 * 搜索索引
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	public List<Blog> searchBlog(String q) throws IOException, ParseException, InvalidTokenOffsetsException{
		List<Blog> blogList = new LinkedList<Blog>() ;
		//1.获取目录
		dir = FSDirectory.open(Paths.get(lucenePath, new String[0])) ;
		//2.实例化索引读入器和搜索器
		IndexReader reader = DirectoryReader.open(dir) ;
		IndexSearcher is = new IndexSearcher(reader) ;
		//3.放入查询条件
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer() ;
		
		QueryParser parser = new QueryParser("title",analyzer) ;
		Query query = parser.parse(q) ;
		QueryParser parser2 = new QueryParser("content",analyzer) ;
		Query query2 = parser2.parse(q) ;
		QueryParser parser3 = new QueryParser("keyword",analyzer) ;
		Query query3 = parser3.parse(q) ;
		QueryParser parser4 = new QueryParser("summary",analyzer) ;
		Query query4 = parser4.parse(q) ;
		
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder() ;
		
		booleanQuery.add(query, BooleanClause.Occur.SHOULD) ;//表示或者关系
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD) ;
		booleanQuery.add(query3, BooleanClause.Occur.SHOULD) ;
		booleanQuery.add(query4, BooleanClause.Occur.SHOULD) ;
		
		//4.进行查询
		TopDocs hits = is.search(booleanQuery.build(), 100);//最多返回100条数据
		
		//5.高亮搜索字
		QueryScorer scorer = new QueryScorer(query) ;
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer) ;
		//两个参数，第一个参数，高亮字前格式，第二个参数，高亮字后格式
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>") ;
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer) ;
		highlighter.setTextFragmenter(fragmenter) ;
		
		//6.遍历查询结果，放到blogList 中
		for(ScoreDoc scoreDoc:hits.scoreDocs) {
			Document doc = is.doc(scoreDoc.doc) ;
			Blog blog = new Blog() ;
			blog.setId(Integer.parseInt(doc.get("id"))) ;
			blog.setReleaseDateStr(doc.get("releaseDate")) ;
			
			String title = doc.get("title") ;
			String content = StringEscapeUtils.escapeHtml(doc.get("content")) ;
			String keyword = doc.get("keyword") ;
			String summary = doc.get("summary") ;
			
			if(title != null) {
				String hTitle = highlighter.getBestFragment(analyzer.tokenStream("title", new StringReader(title)), title) ;
				if(StringUtil.isEmpty(hTitle)) {
					blog.setTitle(title) ;
				}else {
					blog.setTitle(hTitle) ;
				}
			}
			if(content != null) {
				String hContent = highlighter.getBestFragment(analyzer.tokenStream("content", new StringReader(content)), content) ;
				if(StringUtil.isEmpty(hContent)) {
					if(content.length() <= 200) {
						blog.setContent(content) ;
					}else {
						blog.setContent(content.substring(0,200)) ;
					}
				}else {
					blog.setContent(hContent) ;
				}
			}
			if(keyword != null) {
				String hKeyword = highlighter.getBestFragment(analyzer.tokenStream("keyword", new StringReader(keyword)), keyword) ;
				if(StringUtil.isEmpty(hKeyword)) {
					blog.setKeyword(keyword) ;
				}else {
					blog.setKeyword(hKeyword) ;
				}
			}
			if(summary != null) {
				String hSummary = highlighter.getBestFragment(analyzer.tokenStream("summary", new StringReader(summary)), summary) ;
				if(StringUtil.isEmpty(hSummary)) {
					blog.setSummary(summary) ;
				}else {
					blog.setSummary(hSummary) ;
				}
			}
			
			blogList.add(blog) ;
		}
		
		return blogList ;
	}
}

