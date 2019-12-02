package Eng_WordList;

public class Contents {
	private String word;
	private String mean;
	private Integer Chapter;

	// 생성자를 단어, 뜻, 챕터별로 받겠다!!
	public Contents(String word, String mean, Integer Chapter) {
		this.word = word;
		this.mean = mean;
		this.Chapter = Chapter;
	}

	// 지금 생성자의 단어만 불러오겠다!!
	public String getWord() {
		return word;
	}

	// 지금 생성자의 단어만 수정하겠다!!
	public void setWord(String word) {
		this.word = word;
	}

	// 지금 생성자의 뜻만 불러오겠다!!
	public String getMean() {
		return mean;
	}

	// 지금 생성자의 뜻만 수정하겠다!!
	public void setMean(String mean) {
		this.mean = mean;
	}

	// 지금 생성자의 챕터만 불러오겠다!!
	public Integer getChapter() {
		return Chapter;
	}

	// 지금 생성자의 챕터만 수정하겠다!!
	public void setChapter(Integer Chapter) {
		this.Chapter = Chapter;
	}
}