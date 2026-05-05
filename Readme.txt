python3 -m venv venv
source venv/bin/activate
pip install notebook
jupyter notebook

Practical 11

✅ 1. Install Hadoop (Linux)
Step 1: Download Hadoop
wget https://downloads.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
Step 2: Extract
tar -xvzf hadoop-3.3.6.tar.gz
mv hadoop-3.3.6 ~/hadoop
Step 3: Set Environment Variables

Open:

nano ~/.bashrc

Add:

export HADOOP_HOME=~/hadoop
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

Apply:

source ~/.bashrc
Step 4: Verify
hadoop version

If this works → setup is correct

✅ 2. Compile Your WordCount.java
javac -classpath "$(hadoop classpath)" WordCount.java
✅ 3. Create JAR
jar -cvf wordcount.jar *.class
✅ 4. Run Hadoop in Local Mode (No HDFS needed)

Create input file:

echo "hello hadoop hello spark" > input.txt
Run Job
hadoop jar wordcount.jar WordCount input.txt output
View Output
cat output/part-r-00000
✅ Expected Output
hello   2
hadoop  1
spark   1




Practical 12
✅ 1. Prerequisites
Check Scala
scala -version
Check Spark
spark-submit --version
❌ If not installed
Install Scala
sudo apt update
sudo apt install scala -y
Install Spark (quick setup)
wget https://downloads.apache.org/spark/spark-3.5.1/spark-3.5.1-bin-hadoop3.tgz
tar -xvzf spark-3.5.1-bin-hadoop3.tgz
mv spark-3.5.1-bin-hadoop3 ~/spark

Add to PATH:

nano ~/.bashrc

Add:

export SPARK_HOME=~/spark
export PATH=$PATH:$SPARK_HOME/bin

Apply:

source ~/.bashrc
✅ 2. Prepare Files
Create LogFileAnalysis.scala
nano LogFileAnalysis.scala

Paste your code.

Create log.txt
nano log.txt

Example:

INFO Application started
ERROR Database failed
WARN Low memory
INFO User login
✅ 3. Run the Program
🔥 Method 1 (Best & Recommended)
spark-submit LogFileAnalysis.scala
🔹 Method 2 (Compile + Run)
scalac LogFileAnalysis.scala
scala LogFileAnalysis
✅ 4. Expected Output
(INFO,2)
(ERROR,1)
(WARN,1)



Practical 13
✅ 1. Prerequisites
Check Spark
spark-submit --version

If not installed → install Spark (same steps as earlier).

✅ 2. Prepare Files
Create Scala file
nano WordCountExample.scala

Paste your corrected code.

Create input file
nano input.txt

Example:

Hello Spark
Hello Scala
Spark is fast
✅ 3. Run the Program
🔥 Recommended Method (Spark way)
spark-submit WordCountExample.scala
✅ Alternative (compile + run)
scalac WordCountExample.scala
scala WordCountExample

⚠️ This may fail if Spark libraries are not linked, so prefer spark-submit.

✅ 4. Expected Output
+-----+-----+
|value|count|
+-----+-----+
|Hello|    2|
|Spark|    2|
|Scala|    1|
|is   |    1|
|fast |    1|
+-----+-----+