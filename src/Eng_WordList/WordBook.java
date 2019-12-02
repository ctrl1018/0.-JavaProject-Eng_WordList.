package Eng_WordList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordBook {

	Scanner scanner = new Scanner(System.in); // 콘솔창에서 입력 받을 수 있게 할꺼야!
	private List<Contents> wordList;

	public WordBook() {
		wordList = new ArrayList<Contents>(); // WordBook에서 만들 생성자들이 ArrayList형식이고 Contents 안에 양식을 이용해 만들겠다!!
	}
	
	public void Run() { // run실행 경우 1,2,3,4의 select menu를 선택해 사용 하도록 설정할꺼야!__ 메인(main_exeution에서 Run이 실행될꺼야!
		int key = 0;
		while ((key = selectMenu()) != 0) { // 이제 입력받을 key를 비교할꺼야
			switch (key) {
			case 1: // key값이 1일때는 단어를 추가하는 기능을 실행시키자~
				add();
				break;
			case 2: // key값이 2일때는 단어를 삭제하는 기능을 실행시키자~
				remove();
				break;
			case 3: // key값이 3일때는 단어를 수정하는 기능을 실행시키자~
				modify();
				break;
			case 4: // key값이 4일때는 단어 보여주기 기능을 실행시키자~
				showList();
				break;
			case 5: // key값이 5일때는 리스트에 있는 단어들을 text파일로 저장 하자~
				save();
				break;
			default: // key값에 다른번호가 들어오면 아래 문장이 출력될꺼야
				System.out.println("없는 메뉴를 선택하셨습니다. 1~4번 까지 선택해주세요.");
				break;
			}
		}
		System.out.println("시스템이 종료 되었습니다."); // key값이 0번이면 종료 시킬꺼야
	}

	public int selectMenu() { // run이 실행 될 때 console화면 창에 보여지도록 해보자__ 거기서 run에 사용할 key값을 입력할꺼야
		System.out.println("1(추가) 2(삭제) 3(수정) 4(단어장보기) 5(저장) 0(종료)");
		int key = scanner.nextInt(); // 사용할 기능을 int형으로 입력받게되면은 key값을 return!
		scanner.nextLine();
		return key;
	}

	public void add() { // addword 실행시 배열에 단어와 뜻을 추가 할 수 있도록 해보자 __ key1번을 입력시 단어추가 메소드인 이게 실행되게!
		System.out.println("<단어장에 추가할 단어와 뜻을 입력해주세요.>");
		System.out.print("단어를 추가 ::");
		String word = scanner.nextLine(); // 입력받을 단어를 Stirng형으로 받아 쓸꺼야

		if (isExists(word)) { // isExists 메소드를 이용해 중복체크를 할꺼야
			System.out.printf("단어가 이미 존재합니다. : " + word + "\n"); // *** : 단어 확인을 추가로 할 필요없이 입력값을 그냥 보여주면 되!
			return; // 위에 있는 단어라고 하니까 return를 선언해 끝낼꺼야
		}

		System.out.print("추가 할 단어의 뜻 입력 ::"); // 만약, 중복단어가 아니면 새롭게 등록할 뜻과 챕터를 입력 할꺼야
		String mean = scanner.nextLine();

		System.out.print("추가 할 단어의 챕터 ::");
		Integer Chapter = scanner.nextInt();

		wordList.add(new Contents(word, mean, Chapter)); // 현재 존재하는 리스트의 맨 밑에 단어를 추가할꺼야
		System.out.println(word + "단어가" + Chapter + " 챕터에 추가되었습니다.");
	}

	public void remove() { // removeword 실행시 원하는 인덱스 int형으로 입력을 하면은 배열 인덱스에 맞게 삭제가 되도록 하고__ key 2번을 눌렀을 시
								// 단어삭제 메소드가 실행되게!
		System.out.print("삭제할 인덱스나 단어 입력 ::");
		String input = scanner.nextLine();

		try {
			if (Integer.valueOf(input) % 1 == 0) { // 만약 숫자를 입력했으면 이방법으로 지울꺼야
				int num = Integer.valueOf(input);
				wordList.remove(num);
				System.out.println("No." + num + "의 단어가 삭제되었습니다.");
				return;
			}
		}

		catch (NumberFormatException e) { // 문자로 입력했으면 이 방법으로 지울꺼야
			if (indexOfWord(input) >= 0) {
				wordList.remove(indexOfWord(input));
				System.out.println(input + " 단어가 삭제되었습니다.");
				return;
			}
		} catch (IndexOutOfBoundsException e) { // 단어장에 있는 인덱스 번호보다 클 경우 말해주자!
			System.out.println("없는 인덱스 번호입니다.");
			return;
		}
		System.out.println("입력한 단어의 형식이 일치하지 않습니다."); // 리스트에 존재하지 않는 단어면 이렇게 말해주자!
	}

	public void modify() { // modifyword 실행시 단어를 배열에서 찾은 후 없을 경우 존재하지 않는 단어를 출력하게 하자__ key 4번을 눌렀을 때 수정메소드가 실행되게
		System.out.println("<수정 될 뜻의 단어를 입력하세요>.");
		System.out.print("단어 :: ");
		String findword = scanner.nextLine();

		boolean isFind = isExists(findword); // 아래의 boolean isFind = false로 찾았어? isFind 찾아냈어!!
		if (isFind) { // 만약 isFind에서 중복된 것이 있다면?!
			System.out.println("수정 할 뜻 :: ");
			String modifymean = scanner.nextLine();

			System.out.println("챕터 이동하기 :: ");
			Integer Chapter = scanner.nextInt();

			for (Contents word : wordList) { // wordList에서 반복적으로 검사? 를 통해 문자를 비교하자!
				if (word.getWord().equals(findword)) { // 비교해서 있다면 글자를 교체!
					word.setMean(modifymean);
					word.setChapter(Chapter);
					System.out.println(word.getMean() + ", Chp." + word.getChapter() + " (으)로 수정이 완료되었습니다.");
					break; // 만약에 해당사항이 if문 에 해당되는 사항이 없다면, 빠져나와서 존재 하지 않는다는걸 알리자!
				}
				continue; // 있다면 계속 반복해서 가자!
			}
		} else {
			System.out.println("< 존재하지 않는 단어 입니다. >");
		}
	}

	public void showList() { // 배열에 저장된 데이터 값을 전체다 불러와 console화면에 보여주자 __ key 3번 입력시 리스트 출력 메소드가 실행될꺼야
		System.out.println("<전체 목록 보기>");
		System.out.println("총 단어 수 : " + wordList.size() + "개"); // text → arraylist에 저장된 단어들의 총 갯수를 보여줄게

		for (int i = 0; i < wordList.size(); i++) { // 단어가 너무 많아서 10개씩 보여주려고해
			if (i != 0 && i % 10 == 0) { 
				System.out.println("계속 보시려면 엔터를 입력하세요. (다른 키 입력시 중지)"); //인덱스가 10의 배수 일 때마다 엔터입력을 확인할꺼야

				if (!scanner.nextLine().isEmpty()) // 다른 문자열을 입력하고 엔터(isEmpty_공백 관련 비교?!)를 입력시 중지시킬꺼야
					break;
			}
			Contents word = wordList.get(i); // word 생성자 안에 wordlist의 i인덱스의 값들을 집어넣을거야
			System.out.println(
					"No." + i + " [" + word.getWord() + ", " + word.getMean() + "]" + " Chapter " + word.getChapter());
		}
	}

	public void save() {
	      Path file = Paths.get("eng_dic1.txt"); //어디에 저장할껀데? 
	      try { // 리스트에 있는 단어들을 text 파일에 적을꺼야 
	         BufferedWriter writer = Files.newBufferedWriter(file, Charset.forName("euc-kr"));
	         
	         for (Contents word : wordList) { // 다시 text -> 리스트로 불러 올 수도 있으니까 같은 방식 (단어 + 탭 + 뜻 + 탭 + 챕터번호)으로 저장하려고해 
	            writer.write(word.getWord());
	            writer.write("\t");
	            writer.write(word.getMean());
	            writer.write("\t");
	            writer.write(String.valueOf(word.getChapter()));
	            writer.flush();
	            writer.newLine();
	         }
	         writer.close(); // 쓰기를 종료할꺼야
	      } catch (IOException e) { // 입력값이 잘못됬을 시
	         System.out.println("파일을 읽을 수 없습니다.");
	      }   
	      System.out.println("<eng_dic1.txt파일로 덮어쓰기(저장)되었습니다.>"); // 저장완료했어!!
	   }
	
	public void Load() { // 메인(main_exeution)에 있는 load함수야 밖에있는 text파일을 리스트에 저장 할꺼야
		try { // 먼저 text를 읽고
			BufferedReader reader = Files.newBufferedReader(Paths.get("eng_dic.txt"), Charset.forName("euc-kr"));
			String line = null;

			while ((line = reader.readLine()) != null) { //text파일에 적힌것들을 리스트에 넣을꺼야__한줄씩 읽어올껀데 비어있는 줄이면 읽지 않아
				String[] splitedStr = line.split("\t"); // splitedStr 배열에 \t를 기준으로 하나씩 넣을꺼야
				
				if (splitedStr.length == 3) { // 그렇게 넣은 splitedStr 배열의 길이가 3일 때 wordlist에 추가할꺼야
					int Chapter = Integer.valueOf(splitedStr[2]);

					String Word = splitedStr[0];
					String Mean = splitedStr[1];

					Contents content = new Contents(Word, Mean, Chapter);

					wordList.add(content);
				}
			}
			reader.close(); // 다 읽었으면 종료하자
		} catch (FileNotFoundException fnf) { // 파일의 이름이 잘못되어있으면 아래 출력
			fnf.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private boolean isExists(String wordStr) {
		for (Contents word : wordList) { // 리스트에 있는 단어와 내가 입력한 단어를 비교해서 있으면 true를 리턴
			if (word.getWord().equals(wordStr))
				return true;
		}
		return false; // 없다면 false를 리턴해!
	}

	private int indexOfWord(String wordStr) { // index번호를 찾는 메소드야
		for (int i = 0; i < wordList.size(); i++) { 
			if (wordList.get(i).getWord().equals(wordStr)) // 내가 입력한 단어의 index를 찾았어!!
				return i;
		}

		return -1; // 리스트에 없는 단어이면 -1를 return해!!
	}

	public void findWord(String word) { //

		// 1. word값이 존재하는지 여부
		if (isExists(word)) {
			System.out.println(word + "값이 존재합니다!");
		} else {
			System.out.println(word + "값이 없네요...");
		}

		// 2. word값의 인덱스를 얻어오기
		int index = indexOfWord(word);
		if (index > -1) { // 해당범위에 벗어나면 -1을 return해 즉, 존재하지 않는다는 뜻이 index 번호가 -1보다 크면 번호출력, 없으면 존재하지 않으면 출력
			System.out.println(word + "의 인덱스 값 : " + index);
		} else {
			System.out.println(word + "가 리스트에 존재하지 않습니다.");
		}
	}

}
