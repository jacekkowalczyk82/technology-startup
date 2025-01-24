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
    # base64_string = " R2Vla3NGb3JHZWVrcyBpcyB0aGUgYmVzdA =="  
    base64_bytes = base64_string.encode("UTF-8")  
  
    decoded_string_bytes = base64.b64decode(base64_bytes)  
    decoded_string = decoded_string_bytes.decode("UTF-8")  
  
    print(f"Base64: {base64_string} Decoded: {decoded_string}")  
    return decoded_string  

def encode_file(file_path):
    with  open(file_path, "rb") as  imagefile:
        convert  =  base64.b64encode(imagefile.read())
        print(convert.decode('utf-8'))
        save_to_file(file_path+  "_base64.txt", convert.decode('utf-8')) 

    print("encode file: " + file_path + " DONE")
  
def save_to_file(file_path, content):
    file  =  open(file_path, mode="w")
    file.write(content)
    file.close()
    print("save_to_file "+ file_path + " DONE")

def decode_file(base64_file):
    
    output_zip = base64_file + '_decoded.bin'

    # Step 1: Read the Base64 content
    with open(base64_file, 'r') as file:
        base64_content = file.read()

    # Step 2: Decode the Base64 content
    decoded_data = base64.b64decode(base64_content)

    # Step 3: Save the decoded content as a ZIP file
    with open(output_zip, 'wb') as zip_file:
        zip_file.write(decoded_data)

    print(f"Decoded data saved to {output_zip}")


    print("decode_file: " + base64_file + " DONE")

def save_to_binary_file(file_path, content):
    file  =  open(file_path, mode="wb")
    file.write(content)
    file.close()
    print("save_to_binary_file "+ file_path + " DONE")

# Press the green button in the gutter to run the script.  
if __name__ == '__main__':  
    print_hi('PyCharm')  
    encode(TEST_STRING)
    encode_file("/home/jacek/Downloads/deck.js-latest.zip")
    decode_file("/home/jacek/Downloads/deck.js-latest.zip_base64.txt")

    encode_file("/home/jacek/git/workday-go/bin/workday-1.2-20241218-windows-amd64.exe")
    decode_file("/home/jacek/git/workday-go/bin/workday-1.2-20241218-windows-amd64.exe_base64.txt")

    encode_file("/home/jacek/Documents/klucze-hasla/my-secret-app-coba-2025-01/target/x86_64-pc-windows-gnu/release/my-secret-app.exe")
    decode_file("/home/jacek/Documents/klucze-hasla/my-secret-app-coba-2025-01/target/x86_64-pc-windows-gnu/release/my-secret-app.exe_base64.txt")

    encode_file("/home/jacek/Documents/klucze-hasla/go-secret-app-coba-2025-01/bin/go-secret-app-0.1-COBA-2025-01-windows-amd64.exe")
    decode_file("/home/jacek/Documents/klucze-hasla/go-secret-app-coba-2025-01/bin/go-secret-app-0.1-COBA-2025-01-windows-amd64.exe_base64.txt")

    

    

