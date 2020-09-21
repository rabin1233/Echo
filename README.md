# Echo

Echo is a simple framework for building the client-server applications.The echo client is a simple console user interface that perpetually prompts its user forwards the request to a server, then displays the server’s response. Upon receiving a request, the server spawns a request handler thread. Connected to the client, then resumes listening for more requests. The request handler’s run method begins a request-response-loop with the client. The loop ends when the client sends the “quit” request. 

