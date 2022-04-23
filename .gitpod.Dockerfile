FROM gitpod/workspace-full


RUN sdk install java 17.0.2-tem && sdk use java 17.0.2-tem

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh "