package com.prominentpixel;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvIndexingDF {
    public static void main(String[] args) throws IOException {
        Directory directory= FSDirectory.open(Paths.get("/home/pp-5/indexing1"));
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig);
        List<String> files= Files.readAllLines(Paths.get("/home/pp-5/Downloads/ELK-20201022T063954Z-001/ELK/requirement-1-Lucene/df.csv"));
        for (String results:files)
        {
            //String str=results.replace("\"","");
            String data[]=results.split(",");
            add(indexWriter,data[0],data[1],data[2]);
            System.out.println(data[2]);
        }
        indexWriter.commit();
        indexWriter.close();


    }
   public static void add(IndexWriter writer,String date,String title,String content) throws IOException {
        Document document=new Document();
        document.add(new StringField("Date",date, Field.Store.YES));
        document.add(new StringField("Title",title,Field.Store.YES));
        document.add(new StringField("content",content,Field.Store.YES));
        writer.addDocument(document);
    }
}
