FROM java:11-jre
MAINTAINER Egor Barkovsky <woldermind@gmail.com>
ADD ./target/wldrmnd-smlr.jar /app/
CMD ["java", "-jar", "/app/wldrmnd-smlr.jar"]
EXPOSE 8080
