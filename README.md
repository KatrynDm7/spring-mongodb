<h1>Spring + MongoDB</h1>

Tutorial was taken from this post:http://habrahabr.ru/post/217391/

<h2>What was changed:</h2>
<ol>
    <li>Versions of jdk (1.8), spring (4.2.3.RELEASE), spring.mongodb (1.8.2.RELEASE) were updated</li>
    <li>Fixed wrong base-package</li>
</ol>

<h2>Requirements for working with MongoDB:</h2>
<ol>
    <li>You could change db settings at database.properties</li>
    <li>Before starting work with app you should create collection "sequences" and add this data:
        <p>db.createCollection("sequences")</p>
        <p>db.sequences.insert({"_id" : "contacts", "sequence" : 0 })</p>
    </li>
    <li>Second collection "contacts" will be created automatically when you will add a contact info through the contact form</li>
</ol>
