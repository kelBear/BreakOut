JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	
CLASSES = \
		Ball.java \
		Block.java \
		Breakout.java \
		Controller.java \
		Model.java \
		Paddle.java \
		View.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
