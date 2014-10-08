hw2-xinyunzh
============
This project is mainly about how to implement an aggregate analysis engine based on UIMA framework. 

The aggregate analysis engine consists of three annotators. 

* The first one is based on LingPipe, which recognize the name entity based on the metric of confidence by **Confidence Named Entity Chunking**. 

* The second annotator is based on Abner. This annotator will give each entity a special tag based on its catagory of biological terms.

* The last annotator will merge the results produced by the previous two analysis engines. In terms of the threshold of confidence, all of the entities which have a confidence below that threshold will be excluded from the results given by LingPipe-based annotator. However, the corresponding results from Abner-based annotator are taken into consideration by the last annotator, which may increase the F-measure result.