# JSMultiThreadExecutor
<a href="https://www.codacy.com/app/ValeriiOsmachko/JSMultiThreadExecutor?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ValeriiOsmachko/JSMultiThreadExecutor&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/4606f4f2524a4a3296ff46b6309beb8a"/></a>
<a href="https://travis-ci.org/ValeriiOsmachko/JSMultiThreadExecutor"><img src="https://travis-ci.org/ValeriiOsmachko/OnlineShop.svg?branch=master" alt="Build Status" /></a>
<br>
<strong>Project description:</strong><br>
The main purpose of this program is execution of JavaScript code in a separate thread using built-in in java ScriptEngine(Nashorn).
This application was implemented in 2 ways:
<ul>
<li>Runnable</li>
<li>Callable + Executors</li>
</ul>

<strong>Project requirements:</strong><br>
Create REST API wrapper for javax.script.ScriptEngine, which will allow starting javascript code in nashorn(java 8) engine in the separate thread. JS code must be passed in the body of POST method. Application has to have next functionality:
<ol>
<li>Execute script</li>
<li>Show all scripts</li>
<li>Show status of execution</li>
<li>Force kill(stop) of thread</li>
<li>Delete script</li>
<li>Show console out put(if it is) and returned value(if it is)</li>
</ol>


# <strong>Technologies:</strong>
<ul>
<li>java 8</li>
<li>Stream API</li>
<li>Maven</li>
<li>Spring
<ul>
     <li>core</li>
     <li>mvc</li>
    </ul>
</li>
<li>lombok</li>
<li>Tomcat</li>
</ul>

# <strong>How to use?</strong>
<ol>
<li>Make <strong>git clone</strong> of this repository</li>
<li>Open project with your IDE</li>
<li>Wait, until all dependencies will be downloaded</li>
<li>Open tomcat plugin and execute command <strong>tomcat7:run-war</strong></li>
<li>Download <strong>Advanced Rest Chrome Plugin</strong> for Google Chrome(You can use other plugin or tool)</li>
<li>Send requests and get responses :)</li>
</ol>

<strong>A few screenshots of program execution: </strong>

<a href="https://ibb.co/cyDUiv"><img src="https://preview.ibb.co/jYTJbF/screen1.png" alt="screen1" border="0"></a>

After sending request to: {localhost}/stop/{id}

<a href="https://ibb.co/hScBwF"><img src="https://preview.ibb.co/ixU23v/screen2.png" alt="screen2" border="0"></a><br />
