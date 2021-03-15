#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> pref;
string s;


static int reverseIdx(int idx) {
    return s.length() - idx - 1;
}

static int maxPrefix(int size) {
    int start = reverseIdx(size - 1);

    int maxPref = 0;
    pref[0] = -1;

    for (int i = 2; i <= size; i++) {
        int curLen = pref[i-1];
        while (curLen != -1) {
            if (s[start + i-1] == s[start + curLen]) {
                break;
            }
            curLen = pref[curLen];
        }
        pref[i] = curLen + 1;
        if (pref[i] > maxPref) {
            maxPref = pref[i];
        }
    }

    return maxPref;
}

int main() {
	cin >> s;
	reverse(s.begin(), s.end());
	pref.resize(s.length() + 1);
	int ans = 1;
	cout << ans << endl;
	for (int i = 2; i <= (int)s.length(); i++) {
		ans += i - maxPrefix(i);
		cout << ans << endl;
	}
}
