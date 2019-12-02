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

	Scanner scanner = new Scanner(System.in); // �ܼ�â���� �Է� ���� �� �ְ� �Ҳ���!
	private List<Contents> wordList;

	public WordBook() {
		wordList = new ArrayList<Contents>(); // WordBook���� ���� �����ڵ��� ArrayList�����̰� Contents �ȿ� ����� �̿��� ����ڴ�!!
	}
	
	public void Run() { // run���� ��� 1,2,3,4�� select menu�� ������ ��� �ϵ��� �����Ҳ���!__ ����(main_exeution���� Run�� ����ɲ���!
		int key = 0;
		while ((key = selectMenu()) != 0) { // ���� �Է¹��� key�� ���Ҳ���
			switch (key) {
			case 1: // key���� 1�϶��� �ܾ �߰��ϴ� ����� �����Ű��~
				add();
				break;
			case 2: // key���� 2�϶��� �ܾ �����ϴ� ����� �����Ű��~
				remove();
				break;
			case 3: // key���� 3�϶��� �ܾ �����ϴ� ����� �����Ű��~
				modify();
				break;
			case 4: // key���� 4�϶��� �ܾ� �����ֱ� ����� �����Ű��~
				showList();
				break;
			case 5: // key���� 5�϶��� ����Ʈ�� �ִ� �ܾ���� text���Ϸ� ���� ����~
				save();
				break;
			default: // key���� �ٸ���ȣ�� ������ �Ʒ� ������ ��µɲ���
				System.out.println("���� �޴��� �����ϼ̽��ϴ�. 1~4�� ���� �������ּ���.");
				break;
			}
		}
		System.out.println("�ý����� ���� �Ǿ����ϴ�."); // key���� 0���̸� ���� ��ų����
	}

	public int selectMenu() { // run�� ���� �� �� consoleȭ�� â�� ���������� �غ���__ �ű⼭ run�� ����� key���� �Է��Ҳ���
		System.out.println("1(�߰�) 2(����) 3(����) 4(�ܾ��庸��) 5(����) 0(����)");
		int key = scanner.nextInt(); // ����� ����� int������ �Է¹ްԵǸ��� key���� return!
		scanner.nextLine();
		return key;
	}

	public void add() { // addword ����� �迭�� �ܾ�� ���� �߰� �� �� �ֵ��� �غ��� __ key1���� �Է½� �ܾ��߰� �޼ҵ��� �̰� ����ǰ�!
		System.out.println("<�ܾ��忡 �߰��� �ܾ�� ���� �Է����ּ���.>");
		System.out.print("�ܾ �߰� ::");
		String word = scanner.nextLine(); // �Է¹��� �ܾ Stirng������ �޾� ������

		if (isExists(word)) { // isExists �޼ҵ带 �̿��� �ߺ�üũ�� �Ҳ���
			System.out.printf("�ܾ �̹� �����մϴ�. : " + word + "\n"); // *** : �ܾ� Ȯ���� �߰��� �� �ʿ���� �Է°��� �׳� �����ָ� ��!
			return; // ���� �ִ� �ܾ��� �ϴϱ� return�� ������ ��������
		}

		System.out.print("�߰� �� �ܾ��� �� �Է� ::"); // ����, �ߺ��ܾ �ƴϸ� ���Ӱ� ����� ��� é�͸� �Է� �Ҳ���
		String mean = scanner.nextLine();

		System.out.print("�߰� �� �ܾ��� é�� ::");
		Integer Chapter = scanner.nextInt();

		wordList.add(new Contents(word, mean, Chapter)); // ���� �����ϴ� ����Ʈ�� �� �ؿ� �ܾ �߰��Ҳ���
		System.out.println(word + "�ܾ" + Chapter + " é�Ϳ� �߰��Ǿ����ϴ�.");
	}

	public void remove() { // removeword ����� ���ϴ� �ε��� int������ �Է��� �ϸ��� �迭 �ε����� �°� ������ �ǵ��� �ϰ�__ key 2���� ������ ��
								// �ܾ���� �޼ҵ尡 ����ǰ�!
		System.out.print("������ �ε����� �ܾ� �Է� ::");
		String input = scanner.nextLine();

		try {
			if (Integer.valueOf(input) % 1 == 0) { // ���� ���ڸ� �Է������� �̹������ ���ﲨ��
				int num = Integer.valueOf(input);
				wordList.remove(num);
				System.out.println("No." + num + "�� �ܾ �����Ǿ����ϴ�.");
				return;
			}
		}

		catch (NumberFormatException e) { // ���ڷ� �Է������� �� ������� ���ﲨ��
			if (indexOfWord(input) >= 0) {
				wordList.remove(indexOfWord(input));
				System.out.println(input + " �ܾ �����Ǿ����ϴ�.");
				return;
			}
		} catch (IndexOutOfBoundsException e) { // �ܾ��忡 �ִ� �ε��� ��ȣ���� Ŭ ��� ��������!
			System.out.println("���� �ε��� ��ȣ�Դϴ�.");
			return;
		}
		System.out.println("�Է��� �ܾ��� ������ ��ġ���� �ʽ��ϴ�."); // ����Ʈ�� �������� �ʴ� �ܾ�� �̷��� ��������!
	}

	public void modify() { // modifyword ����� �ܾ �迭���� ã�� �� ���� ��� �������� �ʴ� �ܾ ����ϰ� ����__ key 4���� ������ �� �����޼ҵ尡 ����ǰ�
		System.out.println("<���� �� ���� �ܾ �Է��ϼ���>.");
		System.out.print("�ܾ� :: ");
		String findword = scanner.nextLine();

		boolean isFind = isExists(findword); // �Ʒ��� boolean isFind = false�� ã�Ҿ�? isFind ã�Ƴ¾�!!
		if (isFind) { // ���� isFind���� �ߺ��� ���� �ִٸ�?!
			System.out.println("���� �� �� :: ");
			String modifymean = scanner.nextLine();

			System.out.println("é�� �̵��ϱ� :: ");
			Integer Chapter = scanner.nextInt();

			for (Contents word : wordList) { // wordList���� �ݺ������� �˻�? �� ���� ���ڸ� ������!
				if (word.getWord().equals(findword)) { // ���ؼ� �ִٸ� ���ڸ� ��ü!
					word.setMean(modifymean);
					word.setChapter(Chapter);
					System.out.println(word.getMean() + ", Chp." + word.getChapter() + " (��)�� ������ �Ϸ�Ǿ����ϴ�.");
					break; // ���࿡ �ش������ if�� �� �ش�Ǵ� ������ ���ٸ�, �������ͼ� ���� ���� �ʴ´ٴ°� �˸���!
				}
				continue; // �ִٸ� ��� �ݺ��ؼ� ����!
			}
		} else {
			System.out.println("< �������� �ʴ� �ܾ� �Դϴ�. >");
		}
	}

	public void showList() { // �迭�� ����� ������ ���� ��ü�� �ҷ��� consoleȭ�鿡 �������� __ key 3�� �Է½� ����Ʈ ��� �޼ҵ尡 ����ɲ���
		System.out.println("<��ü ��� ����>");
		System.out.println("�� �ܾ� �� : " + wordList.size() + "��"); // text �� arraylist�� ����� �ܾ���� �� ������ �����ٰ�

		for (int i = 0; i < wordList.size(); i++) { // �ܾ �ʹ� ���Ƽ� 10���� �����ַ�����
			if (i != 0 && i % 10 == 0) { 
				System.out.println("��� ���÷��� ���͸� �Է��ϼ���. (�ٸ� Ű �Է½� ����)"); //�ε����� 10�� ��� �� ������ �����Է��� Ȯ���Ҳ���

				if (!scanner.nextLine().isEmpty()) // �ٸ� ���ڿ��� �Է��ϰ� ����(isEmpty_���� ���� ��?!)�� �Է½� ������ų����
					break;
			}
			Contents word = wordList.get(i); // word ������ �ȿ� wordlist�� i�ε����� ������ ��������ž�
			System.out.println(
					"No." + i + " [" + word.getWord() + ", " + word.getMean() + "]" + " Chapter " + word.getChapter());
		}
	}

	public void save() {
	      Path file = Paths.get("eng_dic1.txt"); //��� �����Ҳ���? 
	      try { // ����Ʈ�� �ִ� �ܾ���� text ���Ͽ� �������� 
	         BufferedWriter writer = Files.newBufferedWriter(file, Charset.forName("euc-kr"));
	         
	         for (Contents word : wordList) { // �ٽ� text -> ����Ʈ�� �ҷ� �� ���� �����ϱ� ���� ��� (�ܾ� + �� + �� + �� + é�͹�ȣ)���� �����Ϸ����� 
	            writer.write(word.getWord());
	            writer.write("\t");
	            writer.write(word.getMean());
	            writer.write("\t");
	            writer.write(String.valueOf(word.getChapter()));
	            writer.flush();
	            writer.newLine();
	         }
	         writer.close(); // ���⸦ �����Ҳ���
	      } catch (IOException e) { // �Է°��� �߸����� ��
	         System.out.println("������ ���� �� �����ϴ�.");
	      }   
	      System.out.println("<eng_dic1.txt���Ϸ� �����(����)�Ǿ����ϴ�.>"); // ����Ϸ��߾�!!
	   }
	
	public void Load() { // ����(main_exeution)�� �ִ� load�Լ��� �ۿ��ִ� text������ ����Ʈ�� ���� �Ҳ���
		try { // ���� text�� �а�
			BufferedReader reader = Files.newBufferedReader(Paths.get("eng_dic.txt"), Charset.forName("euc-kr"));
			String line = null;

			while ((line = reader.readLine()) != null) { //text���Ͽ� �����͵��� ����Ʈ�� ��������__���پ� �о�ò��� ����ִ� ���̸� ���� �ʾ�
				String[] splitedStr = line.split("\t"); // splitedStr �迭�� \t�� �������� �ϳ��� ��������
				
				if (splitedStr.length == 3) { // �׷��� ���� splitedStr �迭�� ���̰� 3�� �� wordlist�� �߰��Ҳ���
					int Chapter = Integer.valueOf(splitedStr[2]);

					String Word = splitedStr[0];
					String Mean = splitedStr[1];

					Contents content = new Contents(Word, Mean, Chapter);

					wordList.add(content);
				}
			}
			reader.close(); // �� �о����� ��������
		} catch (FileNotFoundException fnf) { // ������ �̸��� �߸��Ǿ������� �Ʒ� ���
			fnf.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private boolean isExists(String wordStr) {
		for (Contents word : wordList) { // ����Ʈ�� �ִ� �ܾ�� ���� �Է��� �ܾ ���ؼ� ������ true�� ����
			if (word.getWord().equals(wordStr))
				return true;
		}
		return false; // ���ٸ� false�� ������!
	}

	private int indexOfWord(String wordStr) { // index��ȣ�� ã�� �޼ҵ��
		for (int i = 0; i < wordList.size(); i++) { 
			if (wordList.get(i).getWord().equals(wordStr)) // ���� �Է��� �ܾ��� index�� ã�Ҿ�!!
				return i;
		}

		return -1; // ����Ʈ�� ���� �ܾ��̸� -1�� return��!!
	}

	public void findWord(String word) { //

		// 1. word���� �����ϴ��� ����
		if (isExists(word)) {
			System.out.println(word + "���� �����մϴ�!");
		} else {
			System.out.println(word + "���� ���׿�...");
		}

		// 2. word���� �ε����� ������
		int index = indexOfWord(word);
		if (index > -1) { // �ش������ ����� -1�� return�� ��, �������� �ʴ´ٴ� ���� index ��ȣ�� -1���� ũ�� ��ȣ���, ������ �������� ������ ���
			System.out.println(word + "�� �ε��� �� : " + index);
		} else {
			System.out.println(word + "�� ����Ʈ�� �������� �ʽ��ϴ�.");
		}
	}

}
