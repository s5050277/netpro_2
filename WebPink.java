/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebPink {

	private JFrame jf;
	private JTextField url,url1;
	private JEditorPane editorPane,editorPane1;
	private JButton loadButton;
	private JScrollPane js,js1,js2,js3;
	private JTextArea textArea, textArea1;

	public static void main(String[] args) {
		new WebPink();
	}

	public WebPink() {
		jf = new JFrame("Yanisa Cheoypant s5050277@kmitl.ac.th - Java Web Browser (Network Programming Class Assignment)");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1200,1200);

		url = new JTextField();

		url1 = new JTextField();

		loadButton = new JButton("GET PAGE");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String a = url.getText().toLowerCase();
				String a1 = url1.getText().toLowerCase();
				if(!a.startsWith("http://") && !a.startsWith("https://"))
					a = "http://" + a;
				if(!a1.startsWith("http://") && !a1.startsWith("https://"))
					a1 = "http://" + a1;
				setPage(a);
				setPage2(a1);
			}
		});


		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					url.setText(event.getURL().toString());
				}
			}
		});

		editorPane1 = new JEditorPane();
		editorPane1.setEditable(false);
		editorPane1.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					url1.setText(event.getURL().toString());
				}
			}
		});

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);

		textArea1 = new JTextArea();
		textArea1.setLineWrap(true);
		textArea1.setEditable(false);
		textArea1.setWrapStyleWord(true);


		js = new JScrollPane(editorPane);
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js1 = new JScrollPane(editorPane1);
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js2 = new JScrollPane(textArea);
		js2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js3 = new JScrollPane(textArea1);
		js3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		
		
		
		JLabel la= new JLabel("URL :");
		JPanel j1 = new JPanel(new BorderLayout());
		j1.add(url, BorderLayout.CENTER);
		j1.add(la, BorderLayout.WEST);

		JLabel la1 = new JLabel("URL :");
		JPanel j2 = new JPanel(new BorderLayout());
		j2.add(url1, BorderLayout.CENTER);
		j2.add(la1, BorderLayout.WEST);
		j2.add(loadButton, BorderLayout.EAST);
                
                JPanel j10 = new JPanel(new BorderLayout());
		j10.add(js, BorderLayout.NORTH);

		JPanel j3 = new JPanel(new BorderLayout());
		j3.add(j1, BorderLayout.NORTH);
		j3.add(js, BorderLayout.CENTER);

		JPanel j4 = new JPanel(new BorderLayout());
		j4.add(j2, BorderLayout.NORTH);
		j4.add(js1, BorderLayout.CENTER);

		JPanel j5 = new JPanel(new BorderLayout());
		j5.add(j3, BorderLayout.WEST);
		j5.add(j4, BorderLayout.EAST);

		JPanel j6 = new JPanel(new BorderLayout());
		j6.add(js2, BorderLayout.WEST);
		j6.add(js3, BorderLayout.EAST);

		jf.add(j5, BorderLayout.CENTER);
		jf.add(j6, BorderLayout.SOUTH);

		jf.setVisible(true);

		setSize();

	}

	private void setPage(final String url) {
		setText(textArea, "THREAD 1", "Get");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					setText(textArea, "THREAD 1", "RUN URL = "+url);
					printHeader(textArea1, "THREAD 1", url);
					editorPane.setPage(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				setText(textArea, "THREAD 1", "Teminate");
			}
		});
		t.start();
	}

	private void setPage2(final String url) {
		setText(textArea, "THREAD 2", "Get");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					setText(textArea, "THREAD 2", "RUN URL = "+url);
					printHeader(textArea1, "THREAD 2", url);
					editorPane1.setPage(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				setText(textArea, "THREAD 2", "Teminate");
			}
		});
		t.start();
	}

	private void setText(JTextArea j, String name, String m) {
		j.append(name + " : " + m + "\r\n");
		j.setCaretPosition(j.getText().length());
	}
	
	private void setSize() {
		Dimension d1 = jf.getSize();
		Dimension d2 = new Dimension(d1.width/2, d1.height/3);
		js.setPreferredSize(d2);
		js1.setPreferredSize(d2);
		js2.setPreferredSize(d2);
		js3.setPreferredSize(d2);
	}
	
	private void printHeader(JTextArea j, String name, String url) {
		try {
			URL obj = new URL(url);
			URLConnection conn = obj.openConnection();
		    Map<String, List<String>> map = conn.getHeaderFields();
		    for(Entry<String, List<String>> a : map.entrySet()) {
		    	setText(j, name, a.getKey() + ": " + a.getValue());
		    }
		    List<String> contentLength = map.get("Content-Length");
            if (contentLength == null) {
            	setText(j, name, "'Content-Length' doesn't present in Header!");
            } else {
                for (String header : contentLength) {
                	setText(j, name, "Content-Lenght: " + header);
                }
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}