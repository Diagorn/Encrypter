import React from 'react'
import { Form } from './component/Form/Form';

import './App.css';
import { Container } from 'react-bootstrap';
import { Navigation } from './component/Navigation/Navigation';

function App() {
  return (
    <div className="App">
        <Navigation></Navigation>
        <Container>
          <Form/>
        </Container>
    </div>
  );
}

export default App;
