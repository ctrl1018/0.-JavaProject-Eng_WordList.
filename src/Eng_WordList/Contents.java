package Eng_WordList;

public class Contents {
	private String word;
	private String mean;
	private Integer Chapter;

	// �����ڸ� �ܾ�, ��, é�ͺ��� �ްڴ�!!
	public Contents(String word, String mean, Integer Chapter) {
		this.word = word;
		this.mean = mean;
		this.Chapter = Chapter;
	}

	// ���� �������� �ܾ �ҷ����ڴ�!!
	public String getWord() {
		return word;
	}

	// ���� �������� �ܾ �����ϰڴ�!!
	public void setWord(String word) {
		this.word = word;
	}

	// ���� �������� �游 �ҷ����ڴ�!!
	public String getMean() {
		return mean;
	}

	// ���� �������� �游 �����ϰڴ�!!
	public void setMean(String mean) {
		this.mean = mean;
	}

	// ���� �������� é�͸� �ҷ����ڴ�!!
	public Integer getChapter() {
		return Chapter;
	}

	// ���� �������� é�͸� �����ϰڴ�!!
	public void setChapter(Integer Chapter) {
		this.Chapter = Chapter;
	}
}