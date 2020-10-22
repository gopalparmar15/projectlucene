package com.prominentpixel;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class CsvIndeingFP {
    public static void main(String[] args) throws IOException {
        Directory directory= FSDirectory.open(Paths.get("/home/pp-5/indexing"));
        IndexWriterConfig config=new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer=new IndexWriter(directory,config);
        List <String> result= Files.readAllLines(Paths.get("/home/pp-5/Downloads/ELK-20201022T063954Z-001/ELK/requirement-1-Lucene/FP-2019.csv"));
        for (String s:result)
        {
            String string=s.replace("\"","");
            String[] datas=string.split(",");
            add(writer,datas[0],datas[1],datas[2],datas[3],datas[4]);

        }
        writer.commit();
        writer.close();

    }
    private static void add(IndexWriter w, String date, String link, String title, String body_text, String paper_id) throws IOException {
        Document document=new Document();
        document.add(new StringField("date",date,Field.Store.YES));
        document.add(new StringField("link",link,Field.Store.YES));
        document.add(new StringField("title",title,Field.Store.YES));
        document.add(new StringField("body_text",body_text,Field.Store.YES));
        document.add(new StringField("paper_id", String.valueOf(paper_id),Field.Store.YES));
        w.addDocument(document);
    }
}
