# Laboratory Work Nr. 4

### Objectives 
 - Understand the basic concepts of the email systems;
 - Study the SMTP and POP3 protocols.
 
### Task
 * Create a simple email client application which will give the possibility to send and read messages using an email account. The next list
 contains the optional tasks:
   - Send an email with some attachments;
   - Send an html email which will includes pictures;
   - Develop a class _POP3Header_ which will be reasponsible for reading the headers of a message.

### Theory
The email systems can be described as asynchronous and indirect. Here, the messages are sent to an intermediate which ensures the transfer,
but the writing and reading are independent processes.

Next I will try to explain the phases through which a message goes while it is sent to another user:
 1. The user writes a message using an email client(MUA, Mail User Agent) which will ensure the message creation and rendering to a
 message transfer agent(MTA, Mail Transfer Agent). In order to transmit it the SMTP protocol is used.
 2. The MTA will try to determine the recipient's address which represents the address of a server that is reasponsible for receiving and
 redistributing messages(MX, Mail eXchanger). It can be easily viewed in the recipient's domain after the @ symbol. The conversion will
 from a domain name to an IP address will be done by the DNS server.
 3. The MTA server will send the message to the recipient server using the SMTP protocol. The recipient server will redirect it to the
 recipient's inbox.
 4. The recipient user using the POP3 protocol will read the message.
 
SMTP(Simple Mail Transfer Protocol) is an Internet standard for electronic mail(e-mail) transmission across Internet Protocol(IP) networks.
SMTP uses TCP port 25. SMTP connections secured by SSL are known by the shorthand SMTPS, though SMTPS is not a protocol in its own right.
As this protocol started out purely ASCII text-based, it did not deal well with binary files, or characters in many non-English languages.
Because of that standards such as Multipurpose Internet Mail Extensions(MIME) were developed to encode binary files for transfer through
SMTP.

MIME extends the format of email to support:
  - Text in character sets other than ASCII;
  - Non-text attachments: audio, video, images, application programs etc;
  - Message bodies with multiple parts;
  - Header information in non-ASCII character sets.
  
Post Office Protocol (POP) is an application-layer Internet standard protocol used by local e-mail clients to retrieve e-mail from a remote server over a TCP/IP connection. POP supports simple download-and-delete requirements for access to remote mailboxes. A POP server listens on well-known port 110. POP version 3(POP3) is the version in common use.

### Implementation
In order to perform this laboratory works I have used the JavaMail API. It provides a platform-independent and protocol-independent framework to build mail and messaging applications. The JavaMail API provides a set of abstract classes defining objects that comprise a mail system. It is an optional package (standard extension) for reading, composing, and sending electronic messages.

Because of the optional points I've sent a message which incapsulates in itself a simple text message, a message with an attachment and a html formatted message which will include an image. To send it first I got a mail session. Than I created a message of _MimeMessage_ type. I've add to it the so called envelope information which include: recipients and subject. Next, because of the composed structure of the message I created a container of _Multipart_ type which will hold multiple body parts. Further different body parts of the message were created. All of them are of _MimeBodyPart_ type. My message contains 3 body parts: _textBodyPart_, _attachementBodyPart_ and _htmlBodyPart_. After their creation I added them in the _Multipart_ container. The container was passed as an argument to the _setContent_ method which creates the message's content. Now the message is ready to go and I send it using the _SMTPTransport_ object. Using the above mentioned type of object for transportation allows us to send the message via SMTP protocol.

The second part of the laboratory work is dealing with reading of messages. To read email, we would need POP server. I have used Gmail's POP3 Server. As in previous part first thing I have to do is to get a mail session. After I created a _Store_ object that is reasponsible for storing and retrieving messages. Next I obtained a connection to the POP server. After this I created a _Folder_ object. Using it I can get the messages by establishing a _READ_ status for the "Inbox" folder. Now I have access to all the messages from the inbox and I can read them. The reading process can be done in different ways. The information retrieved from the message depends on the need. In my case it is: sender, receiver, subject, sent date, content and headers. In order to read headers I have created _POP3Header_ class. It contains a method which gets all the headers and store them by their name and value.

### Conclusion
In this laboratory work I have gained knowledge about how an email system is working. I have understand the SMTP and POP3 roles in order ensuring the communication between users using an email client.
I have developed a simple email client application which gives the possibility to send and read messages using an email account. The process of creation make me have a better understanding about how a message is formed, send and read.
