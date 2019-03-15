# RestoApp
Application that first allows a restaurant owner to specify the number and
locations of tables and seats in a restaurant as well as the menu and then allows waiters to reserve
tables, place orders, and issue bills. The application will also be able to load a restaurant menu from a file
given to you.

### More Details About RestoApp

A restaurant owner first specifies the number and locations of tables and seats as shown on the left side
in the illustration below. The restaurant owner places the tables and seats according to the layout of the
restaurant to make it easier for waiters to identify tables. You may assume that the restaurant has only
one level. Note that you do not have to implement the exact user interface as shown below, but your
application must be able to perform all required tasks. Furthermore, the shown user interface is not
complete and needs to be extended by you to support all required tasks.
The restaurant owner will also specify the restaurant menu that your application must load and
subsequently display during the order process (see right side in the illustration above).
A waiter uses the RestoApp for three tasks: reservations, orders, and bills. A waiter may reserve one or
more tables for a customer who calls the restaurant to make a reservation. A waiter may take the order
of each customer at a table. A waiter may issue a bill for the whole table, for each customer at a table,
for each arbitrary group of customers at a table, or for each arbitrary group of customers from different
tables.
A customer may reserve tables for a specific date, time, and number of persons. To hold the reservation,
the restaurant requires the name of the customer as well as a phone number or email address as contact
information. In return, the customer is given a reservation number. A customer may cancel the
reservation at any time. The restaurant also accepts walk-ins.
A waiter takes the order of each customer seated at a table. A waiter identifies each table by its number.
The customers have the choice of getting one bill per table or individual bills. Shared items such as a
bottle of water or wine are divided equally among the sharing customers on individual bills. The price of
an item already includes all taxes (note: taxes are out of scope). If a party is large enough to require
several tables, one bill for several tables may also be issued. A bill may also be issued for a group of
customers seated at different tables. It is possible for a customer to change an order by cancelling one
item or adding another. The whole order for a customer or table may also be cancelled.
For legal and accounting reasons, all orders are retained by the RestoApp application. Reservations,
however, are not retained when they are not needed anymore.
