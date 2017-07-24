
FILE=*.csv

# Make command can be run form the root of the project directory where
# build.gradle and gradlew files exist. It will create the jars for all
# the modules inside project
Default:
	./gradlew build

# make local will execute all the jars for sequential run, coarse lock run,
# no lock run, fine lock run and no sharing run.
# It will take the input(.csv) file as argument and that file can be kept 
# at the project root directory where the Makefile exists
local:
	java -jar CoarseLock/build/libs/com.CoarseLock-1.0-SNAPSHOT.jar $(FILE)
	java -jar FineLock/build/libs/com.FineLock-1.0-SNAPSHOT.jar $(FILE)
	java -jar NoSharing/build/libs/com.NoSharing-1.0-SNAPSHOT.jar $(FILE)
	java -jar NoLock/build/libs/com.NoLock-1.0-SNAPSHOT.jar $(FILE)
	java -jar Sequential/build/libs/com.Sequential-1.0-SNAPSHOT.jar $(FILE)

# make coarse will execute only the Coarse Lock jar
coarse:
	java -jar CoarseLock/build/libs/com.CoarseLock-1.0-SNAPSHOT.jar $(FILE)

# make seq will execute only the Sequential jar
seq:
	java -jar Sequential/build/libs/com.Sequential-1.0-SNAPSHOT.jar $(FILE)

# make nol will execute only the No Lock jar
nol:
	java -jar NoLock/build/libs/com.NoLock-1.0-SNAPSHOT.jar $(FILE)

# make finel will execute only the Fine Lock jar
finel:
	java -jar FineLock/build/libs/com.FineLock-1.0-SNAPSHOT.jar $(FILE)

# make noshare will execute only the NoSharing jar
noshare:
	java -jar NoSharing/build/libs/com.NoSharing-1.0-SNAPSHOT.jar $(FILE)

# make clean deletes the jars present inside the build/libs folder for all 
# projects
clean:
	rm -rf CoarseLock/build/libs/com.CoarseLock-1.0-SNAPSHOT.jar
	rm -rf FineLock/build/libs/com.FineLock-1.0-SNAPSHOT.jar
	rm -rf NoSharing/build/libs/com.NoSharing-1.0-SNAPSHOT.jar
	rm -rf NoLock/build/libs/com.NoLock-1.0-SNAPSHOT.jar
	rm -rf Sequential/build/libs/com.Sequential-1.0-SNAPSHOT.jar

