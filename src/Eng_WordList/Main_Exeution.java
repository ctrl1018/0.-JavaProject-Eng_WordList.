package Eng_WordList;

public class Main_Exeution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WordBook wordbook = new WordBook();

		wordbook.Load(); // 단어장의 단어들을 ArrayList로 불러오자!
		wordbook.Run(); // 기능들의 선택지를 실행시키자!
	}

}