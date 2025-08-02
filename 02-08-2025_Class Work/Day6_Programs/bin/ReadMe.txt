

ByteStreams : InputStream / Output Streams

Used to read/write raw binary data(bytes)  : Useful for binary files , images 

Base Classes : - 			FileInputStream , FileOutputStream ((Read/Write))
							BufferedInputStream , BufferedOutputStream(add buffering for efficiency)
							DataInputStream, DataOutputStream(read/write primitive data types)

							

CharacterStreams : Reader / Writer

Used to read/write character data(text) : Useful for text type of data

Base Classes : - 			FileReader , FileWriter ((Basic file text I/O))
							BufferedReader , BufferedWriter(efficient for line-based operations)
							InputStreamReader, OutputStreamWriter(act as a bridge between byte - char streams)
							
Piped Streams : PipedReader , PipedWriter( In threads we use it for thread to thread communication using streams)

File Handling  : with File Class which gives methods as exists() , isDirectory() , getName() etc....
		