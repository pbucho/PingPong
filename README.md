# PingPong #

PingPong is a client-server application to create non-sense text. It uses a text file as a dictionary.
Information exchanging between client and server results in a hash being generated that matches a word in the dictionary.
Then the word is added to a log file and chaos is created.

## Requirements ##

Minimum of Java 8 RE is recommended

## Usage ##

All arguments are mandatory for both server and client

### Server ###

java -jar Server.jar port timeout dic log

* port: integer indicating where the server should listen on
* timeout: the number of milliseconds the server should wait before sending a response back to the client
* dic: location of the dictionary file
* log: location of the log (text output) file

### Client ###

java -jar Client.jar addr port timeout

* addr: the address where the server is located
* port: the port where the server is listening on
* timeout: the number of milliseconds the client should wait before sending a message to the server

## License ##

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
