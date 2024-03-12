#include <iostream>
#include <string.h>
#include <vector>

using namespace std;
int main() {
    string a;
    cin >> a;

    int temp = 0;

    int count1 = 0;
    int count2 = 0;

    temp = !a[0];

    for(int i = 0; i < a.size(); i++){
        if(a[i] == '0'){
            if(temp != a[i]){
                count1++;
                temp = a[i];
            }
        }
        else if(a[i] == '1'){
            if(temp != a[i]){
                count2++;
                temp = a[i];
            }
        }
    }

    if(count1 > count2){
        cout << count2;
    }
    else if(count1 < count2){
        cout << count1;
    }
    else{
        cout << count1;
    }

    return 0;
}