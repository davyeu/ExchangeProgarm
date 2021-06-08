# ExchangeProgarm

This is exchange multiThreading program.
 * ByDefault the server is the "local host" and the connection is in port 7777.
 * The connection is by TCP protocol.
 * The MultiServer receive some  exchange requests from the some clients.
 * for each client the MultiServer create thread(RequestHandleThread) that receive the exchange request from the client
 * and by using the ExchangeRate class he receive the client the exchangeRate.
 *
 *the classes that take part at this program:
 * 1) ExchangeRequest: her job is to create the request from the client entries and calculate the exchange rate by ExchangeRate
 * 2) ExchangeRate: this class have map of coins rate, and she implement the logic of the exchanging.
 * 3) RequestHandleThread: receive  the request and send the answer to the client.
 * 4) Client: handle the user requests by GUI
