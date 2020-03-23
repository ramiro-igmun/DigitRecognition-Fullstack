import React from 'react';

const Article = (props) => {
  return(
    <article>
      <section>
        <header>
          <h4 className='border-bottom border-secondary'>About the Project</h4>
        </header>
        <p>The project consists on a Rest API backend, that holds the neural network architecture, and a frontend, that works just as a GUI to communicate with the API.</p>
        <ul>
          <li>
            <h5>Backend</h5>
            <p>The neural network architecture is built in plain Java, without the use of any additional libraries. On top of the Neural Network, I have built a Rest API using Spring Boot
              with Spring Web.
            </p>
          </li>
          <li>
          <h5>Frontend</h5>
            <p>The front end is a single page application built using ReactJS. In addition to having an UI for the creation and training of a Neural Network, it has a canvas that can get 
              the handwritten digits entered by the user and send the data to the Backend for proccessing.
            </p>
          </li>
        </ul>
      </section>
    </article>
  )
}

export default Article;