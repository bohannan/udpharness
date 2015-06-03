# UDP Test Harness


### Desciption
This application contains a simple communication library that allows the users to send location based information to the server via the UDP protocol.

## Quickstart

To install please make sure you have Java version 1.7 and Apache Maven 3.2.3 installed.

### Building
 To build the application please run the following command from your command line terminal in the project directory.

```bash
$ mvn clean install
```

note: please make sure you all your environmental variables set for Maven properly.

### Running
To execute the application please run the following command from your command line terminal in the project directory.

```bash
$ java -jar target/udp-1.0-SNAPSHOT.jar
```

### Input
To change the input, for the number of message sets, duration in seconds, and UUID, please edit the TestHarness.java file located in:
```bash
$PROJECT_DIR/src/main/java/com/laurencebohannan/interveiw/TestHarness.java
```

## Output
Expected output results of the test will be displayed to the terminal. It will look like the output below:

```bash
Listener has been started
Harness has been started
Harness shutdown
Shutting down Listener

Harness location list contained 100 messages sets while the server contained 100 messages sets.
100.0% of messages received.

Below is a condensed list of the received message sets in order.

{ uuid: de305d54-75b4-431b-adb2-eb6b9e546014
	0:0 = [1]
	1:1 = [2]
	1:0 = [3]
	-1:0 = [4]
	-1:-1 = [5]
	0:0 = [6]
	-1:0 = [7]
	0:1 = [8]
	-1:1 = [9]
	0:1 = [10]
	1:1 = [11]
	-1:0 = [12]
	1:1 = [13]
	-1:0 = [14]
	-1:-1 = [15]
	-1:0 = [16]
	-1:-1 = [17]
	0:-1 = [18]
	0:0 = [19]
	0:1 = [20]
	1:-1 = [21,22]
	-1:1 = [23]
	-1:0 = [24]
	-1:1 = [25]
	0:1 = [26]
	1:0 = [27]
	1:1 = [28]
	0:1 = [29]
	-1:-1 = [30]
	-1:0 = [31,32]
	1:-1 = [33]
	0:0 = [34]
	-1:-1 = [35]
	1:-1 = [36]
	1:1 = [37]
	-1:1 = [38]
	1:-1 = [39,40]
	0:-1 = [41]
	-1:1 = [42]
	-1:-1 = [43]
	-1:0 = [44,45,46]
	0:1 = [47]
	1:-1 = [48]
	-1:0 = [49]
	0:1 = [50]
	1:0 = [51]
	0:1 = [52]
	0:-1 = [53]
	-1:-1 = [54]
	-1:1 = [55]
	1:-1 = [56,57]
	-1:0 = [58]
	0:1 = [59,60]
	0:0 = [61]
	-1:-1 = [62]
	-1:1 = [63]
	0:1 = [64]
	-1:0 = [65]
	1:-1 = [66]
	1:0 = [67]
	0:-1 = [68]
	-1:1 = [69,70,71]
	0:1 = [72,73,74]
	-1:0 = [75]
	0:-1 = [76]
	0:1 = [77]
	1:0 = [78]
	1:1 = [79]
	0:1 = [80]
	0:-1 = [81]
	1:1 = [82]
	-1:1 = [83]
	0:1 = [84]
	-1:-1 = [85]
	0:1 = [86]
	1:0 = [87]
	0:0 = [88]
	1:1 = [89,90]
	0:-1 = [91]
	-1:0 = [92]
	0:-1 = [93]
	0:0 = [94]
	-1:-1 = [95]
	1:0 = [96]
	1:-1 = [97]
	-1:-1 = [98]
	-1:0 = [99]
	1:1 = [100]
}
```
