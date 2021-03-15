#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
    cin.tie(nullptr);
	string a, b;
	cin >> a >> b;
	string s = a + "#" + b;
	string rev_s = b + "#" + a;
	reverse(rev_s.begin(), rev_s.end());

	vector<int> z(s.length());
	vector<int> rev_z(s.length());

	vector<int> ans;
	z[0] = rev_z[0] = 0;

	int maxL = 0;
	int rev_maxL = 0;

	for (int i = 1; i < (int)s.length(); i++) {
		z[i] = min(z[i-maxL], max(0, maxL + z[maxL] - i));

        while (i + z[i] < (int)s.length() && s[i + z[i]] == s[z[i]]) {
            z[i]++;
        }

        if (i + z[i] > maxL + z[maxL]) {
            maxL = i;
        }

        rev_z[i] = min(rev_z[i-rev_maxL], max(0, rev_maxL + rev_z[rev_maxL] - i));

        while (i + rev_z[i] < (int)rev_s.length() && rev_s[i + rev_z[i]] == rev_s[rev_z[i]]) {
            rev_z[i]++;
        }

        if (i + rev_z[i] > rev_maxL + rev_z[rev_maxL]) {
            rev_maxL = i;
        }
	}

	for (int i = (int)a.length() + 1; i <= (int)s.length() - (int)a.length(); i++) {
		if (z[i] == (int) a.length() || z[i] + rev_z[(int) s.length() - i + 1] + 1 == (int) a.length()) {
			ans.push_back(i - (int)a.length());
		}
	}

	cout << ans.size() << endl;
	for (auto i : ans) {
		cout << i << ' ';
	}

	return 0;
}
