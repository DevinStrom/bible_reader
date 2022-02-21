#include <iostream>
#include <string>
#include <vector>
#include <fstream>

#include <algorithm> 
#include <functional>
#include <cctype>

#include <regex>

#include <sstream>
#include <typeinfo>

using namespace std;




//works: 2 Corinthians, 2 Corinthians 5, 2 Corinthians 5:10(-12), John, John 5, John 5:6(-7)
//TODO: doesn't work 2 Kings 5-6, John 3-4. hyphen for printing multiple chapters 



int main(char argc, char ** argv) {

	string book = "";
	string bookPlusPath;

	string chapter = "";
	string verse = "";

	string chpVrs;

	vector<string> finalText;


	// ./bible_reader 2 Corinthians 5:10
	// also test ./bible_reader 2 Corinthians 5
	if (argc == 4) {

		for (int i = 1; i < argc - 1; i++) {
			if (i == 1) {
				book.append(argv[i]);
				book.append(" ");
			}
			else {
				book.append(argv[i]);
			}
		}
		std::transform(book.begin(), book.end(), book.begin(), std::ptr_fun<int, int>(std::toupper));
		cout << "var book: " << book << endl; //output "2 CORINTHIANS"

		//this only accounts for something like 12:9-10 where chp = 12:
		//need to find way if chp
		regex chp("[0-9]+:");
		chpVrs = argv[3];

		//********************************************************************************************************************
		//this is where portion of code goes to find chapters only. ex: 2 Corinthians 12-13
		//something like: if chpVrs is number hyphen number with no colon followed by number
		// if(chpVrs == [numbers]-[numbers][^:\d])
		regex chpHyphen("[0-9]+-[0-9]+");  //this seems to work, doesn't show up when input like 2 Corinthians 12:9-10
		smatch someOtherMatch;
		//while (regex_search(chpVrs, someOtherMatch, chpHyphen)) {
		//	cout << "found hyphen in chapters" << endl;
		//}
		//if chpVrs contains hyphen but is 
		if (regex_match(chpVrs, chpHyphen)) {
			cout << "found hyphen in chapters" << endl;
		}








		//append ":" to string if ":" doesn't exist
		//if (chpVrs.find(":") == string::npos) {
		//	chpVrs.append(":");
		//}
		cout << "var chpVrs 1: " << chpVrs << endl; //output  5:10 


		//find the chapter and verse
		smatch match;
		while (regex_search(chpVrs, match, chp)) {
			//cout << "found " << match.str(0) << endl; 

			chapter = match.str(0);
			cout << "var chapter: " << chapter << endl;  //output 5: 
			chpVrs = match.suffix().str();

			cout << "chpVrs is now: " << chpVrs << endl;
			if (chpVrs.length() > 0) {

			
			/*
			This will convert a string with a hyphen to a vector of ints. so 10-22 becomes <vector>(10, 22). 
			created vector at beginning and push each verse to vector 
					ex:   "2 Corinthians 12:10-11" goes into vector as
							{2 Corinthians 12:10, 2 Corinthians 12:11
						
			*/
				if (chpVrs.find("-") != string::npos) {
					cout << "Found hyphen " << endl;
					regex regexHyphen("-");
					vector<string> out(
						sregex_token_iterator(chpVrs.begin(), chpVrs.end(), regexHyphen, -1),
						sregex_token_iterator()
					);

					vector<int> verseVector;
					for (auto& s : out) {
						verseVector.push_back(stoi(s));
					}
					for (auto& s : verseVector) {
						cout << "vector at ?: " << s << endl;
					}

					//this converts the ints from 2 to however many. 10-22 becomes 10,11,12,13, etc.
					for (int i = verseVector[0]; i <= verseVector[1]; i++) {
						finalText.push_back(chapter + to_string(i));
					}
					
					

				}
				else {
					verse = chpVrs; //no hyphen
					//verse = chpVrs + ":"; //this adds : to end of verse so 25 becomes 25:  this was original way I had program

					//pushing chapter and verse to finalText vector. will loop through at close.
					finalText.push_back(chapter + verse);
				}
			}
			else {
				finalText.push_back(chapter + verse);
			}
			cout << "var verse: " << verse << endl;  
		}

		//everything
		cout << "everything is: " << book + " " + chapter + verse << endl;  

		
	}

	// ./bible_reader psalms 21:6 
	// also works for just ./bible_reader psalms 21
	else if (argc == 3) {

		//example: ./bible_reader 2 Corinthians
		//to account for book that starts with number, following if statement is executed
		//tempBookFirst and TempBookName can be ignored if book doesn't start with 1 or 2
		string tempBookFirst = argv[1];
		string tempBookName = argv[2];
		std::transform(tempBookName.begin(), tempBookName.end(), tempBookName.begin(), std::ptr_fun<int, int>(std::toupper));
		if ((tempBookFirst == "2" || tempBookFirst == "1") &&
			(tempBookName == "CHRONICLES" ||
				tempBookName == "CORINTHIANS" ||
				tempBookName == "JOHN" ||
				tempBookName == "KINGS" ||
				tempBookName == "PETER" ||
				tempBookName == "SAMUEL" ||
				tempBookName == "THESSALONIANS" ||
				tempBookName == "TIMOTHY"
			)) { 
			book = tempBookFirst + " " + tempBookName;

			//*************************************************************
			//no chapter or verse selected, will output entire file. 
			finalText.push_back(chapter + verse);
		}
		else {
			//second argv is book
			book = argv[1];
			//convert to uppercase
			std::transform(book.begin(), book.end(), book.begin(), std::ptr_fun<int, int>(std::toupper));

			regex chp("[0-9]+:");
			chpVrs = argv[2];
			if (chpVrs.find(":") == string::npos) {
				chpVrs.append(":");
			}
			cout << "var chpVrs: " << chpVrs << endl; //output  5:10  
			smatch match;

			//find the chapter and verse
			while (regex_search(chpVrs, match, chp)) {
				//cout << "found " << match.str(0) << endl; 
				chapter = match.str(0);
				cout << "var chapter: " << chapter << endl;  //output 5: 
				chpVrs = match.suffix().str();
				if (chpVrs.length() > 0) {
					verse = chpVrs + ":";

					if (chpVrs.find("-") != string::npos) {
						cout << "Found hyphen " << endl;
						regex regexHyphen("-");
						vector<string> out(
							sregex_token_iterator(chpVrs.begin(), chpVrs.end(), regexHyphen, -1),
							sregex_token_iterator()
						);

						vector<int> verseVector;
						for (auto& s : out) {
							verseVector.push_back(stoi(s));
						}
						for (auto& s : verseVector) {
							cout << "vector at ?: " << s << endl;
						}

						//this converts the ints from 2 to however many. 10-22 becomes 10,11,12,13, etc.
						for (int i = verseVector[0]; i <= verseVector[1]; i++) {
							finalText.push_back(chapter + to_string(i));
						}

					}
					else {
						verse = chpVrs; //no hyphen
						//verse = chpVrs + ":"; //this adds : to end of verse so 25 becomes 25:  this was original way I had program

						//pushing chapter and verse to finalText vector. will loop through at close.
						finalText.push_back(chapter + verse);
					}
				}
				else {
					finalText.push_back(chapter + verse);
				}
				cout << "var verse: " << verse << endl;  
			}
		}
	}
	else if (argc == 2) {
		book = argv[1];
		//convert to upppercase
		std::transform(book.begin(), book.end(), book.begin(), std::ptr_fun<int, int>(std::toupper));
		
		//since there is no chapter or verse requested, this will just output every line from within the book file opened.
		finalText.push_back(chapter + verse);
	}

	bookPlusPath = "books_of_bible/" + book + ".txt"; //this might cause problems with the space in the name...
	cout << "file path: " << bookPlusPath << endl; 
	
	//convert input to uppercase, then add folder and txt
	//reference code from: https://en.cppreference.com/w/cpp/algorithm/transform
	//std::transform(book.begin(), book.end(), book.begin(), std::ptr_fun<int, int>(std::toupper));
	//bookPlusPath = "books_of_bible/" + book + ".txt";


	cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;

	/*
	This was me trying to loop line by line and loop through vector for each verse.
	TODO: above, so, this will work with -. but will it work without? No. Need to push_back chapter + verse to finalText
	*/

	//open the txt file
	ifstream bookToRead(bookPlusPath);

	cout << book << endl;
	string line;
	
	while (getline(bookToRead, line)) {
		for (auto s : finalText) {
			if (line.find(s) == 0) {
				cout << line << endl;
			}
		}
	}



	cout << "----------------------------------------" << endl;  
	/*
	//open the txt file
	ifstream bookToRead(bookPlusPath);

	cout << book << endl;
	string line;
	while (getline(bookToRead, line)) {
		if (line.find(chapter + verse) == 0) {
			cout << line << endl;
		}
	}
	*/


	return 0;
}
