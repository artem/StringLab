#include <iostream>
#include <fstream>

using namespace std; 

int main() {
	ofstream out("e-test.txt");
	for (int i = 0; i < 1000000; i++) {
		out << 'a';
	}
}