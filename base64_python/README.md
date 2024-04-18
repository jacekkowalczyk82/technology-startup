# Base 64 encoding and decoding 

Notepad++ has built in plugin for that 


## Python way 

```python
# This is a sample Python script.  
  
# Press Shift+F10 to execute it or replace it with your code.  
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.  
import base64  
  
TEST_STRING = "this is test text to base64 encoding"  
  
  
def print_hi(name):  
# Use a breakpoint in the code line below to debug your script.  
print(f'Hi, {name}') # Press Ctrl+F8 to toggle the breakpoint.  
  
def encode(sample_string) :  
# sample_string = "GeeksForGeeks is the best"  
sample_string_bytes = sample_string.encode("UTF-8")  
  
base64_bytes = base64.b64encode(sample_string_bytes)  
base64_string = base64_bytes.decode("UTF-8")  
  
print(f"{sample_string} as Base64 encoded string: {base64_string}")  
return base64_string  
  
def decode(base64_string):  
import base64  
  
# base64_string = " R2Vla3NGb3JHZWVrcyBpcyB0aGUgYmVzdA =="  
base64_bytes = base64_string.encode("UTF-8")  
  
decoded_string_bytes = base64.b64decode(base64_bytes)  
decoded_string = decoded_string_bytes.decode("UTF-8")  
  
print(f"Base64: {base64_string} Decoded: {decoded_string}")  
return decoded_string  
  
  
# Press the green button in the gutter to run the script.  
if __name__ == '__main__':  
print_hi('PyCharm')  
encode(TEST_STRING)
```
----

## Tests

```python 
######################### tests
import unittest  
from main import encode  
from main import decode  
from main import TEST_STRING  
  
  
  
class MyTestCase(unittest.TestCase):  
def test_encode(self):  
self.assertEqual("dGhpcyBpcyB0ZXN0IHRleHQgdG8gYmFzZTY0IGVuY29kaW5n", encode(TEST_STRING)) # add assertion here  
  
def test_encode_dupa(self):  
self.assertEqual("ZHVwYQ==", encode("dupa")) # add assertion here  
  
def test_decode(self):  
self.assertEqual(TEST_STRING, decode("dGhpcyBpcyB0ZXN0IHRleHQgdG8gYmFzZTY0IGVuY29kaW5n")) # add assertion here  
  
if __name__ == '__main__':  
unittest.main()

```