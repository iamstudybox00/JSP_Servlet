package model2.mvcboard;

public class MVCBoardDTO
{
	private String idx;
	private String name;
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String ofile;
	private String sfile;
	private int downCount;
	private String pass;
	private int visitCount;
	
	public String getIdx()
	{
		return idx;
	}
	public void setIdx(String idx)
	{
		this.idx = idx;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public java.sql.Date getPostDate()
	{
		return postDate;
	}
	public void setPostDate(java.sql.Date postDate)
	{
		this.postDate = postDate;
	}
	public String getOfile()
	{
		return ofile;
	}
	public void setOfile(String ofile)
	{
		this.ofile = ofile;
	}
	public String getSfile()
	{
		return sfile;
	}
	public void setSfile(String sfile)
	{
		this.sfile = sfile;
	}
	public int getDownCount()
	{
		return downCount;
	}
	public void setDownCount(int downCount)
	{
		this.downCount = downCount;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public int getVisitCount()
	{
		return visitCount;
	}
	public void setVisitCount(int visitCount)
	{
		this.visitCount = visitCount;
	}
	
}
