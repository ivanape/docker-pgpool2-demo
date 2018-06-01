# Pgpool2.

FROM centos:7
MAINTAINER programwar <programwar@msn.com>

RUN yum install -y http://www.pgpool.net/yum/rpms/3.7/redhat/rhel-7-x86_64/pgpool-II-release-3.7-1.noarch.rpm && \
    yum install -y pgpool-II-pg96 && \ 
    yum install -y pgpool-II-pg96-debuginfo && \
    yum install -y pgpool-II-pg96-devel && \ 
    yum install -y pgpool-II-pg96-extensions

# Post Install Configuration.
ADD conf/pcp.conf /etc/pgpool2/pcp.conf
ADD conf/pgpool.conf /etc/pgpool2/pgpool.conf
ADD conf/pool_hba.conf /etc/pgpool2/pool_hba.conf
ADD conf/pool_passwd /etc/pgpool2/pool_passwd
RUN chown 105:110 /etc/pgpool2/pool_passwd

# Start script
ADD start-pgpool2.sh /start-pgpool2.sh
RUN chmod 0755 /start-pgpool2.sh && mkdir -p /var/run/pgpool && chmod 777 /var/run/pgpool
RUN pg_md5 -m -u postgres admin@@090

EXPOSE 9999

# Start the container.
CMD /start-pgpool2.sh
