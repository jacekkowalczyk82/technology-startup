use std::collections::HashMap;
use std::env;

fn main() {
    println!("Your safe passwords!");

    //my secrets
    let mut secrets = HashMap::new();
    secrets.insert("foo".to_string(), "bar".to_string());
    secrets.insert("password".to_string(), "T0pS3cr3t".to_string());



    let args: Vec<String> = env::args().collect();
    dbg!(&args);
    println!("");
    println!("-----------------------------------------------");
    println!("");

    if args.len() == 2 {
        let secret_key = &args[1];
        // println!("Secret value for {secret_key}");
        if secrets.contains_key(secret_key) {
            println!("Secret value for {secret_key} is: {0}", secrets[secret_key]);
        } else {
            println!("Secret not found");
        }
        
    } else {
        
        println!("No parameters provided");
    }
    

    // println!("{0}", secrets["foo"]);
    // println!("{0}", secrets.get("foo").unwrap());

    // println!("{0}", secrets["password"]);
    // println!("{0}", secrets.get("password").unwrap());

    println!("                                               ");
    println!("-----------------------------------------------");

}
