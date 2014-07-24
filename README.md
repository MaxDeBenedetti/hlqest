hlqest
======

An implementation of John F. Morahan's fast Hodges-Lehmann estimator in java

For more reading on the Hodges-Lehmann estimator: http://en.wikipedia.org/wiki/Hodges%E2%80%93Lehmann_estimator
For more reading on Morahan's fast Hodges-Lehmann estimator: http://dl.acm.org/citation.cfm?id=319414

The program runs, but the results are wrong. In my defense, this is an incredibly complex algorithm. I managed to get as far
as creating and partitioning an upper triangular matrix while storing only the row endings. However, what to do after 
the initial partitioning was never satisfactorily explained to me. I am fairly certain that that is where the problem is.
