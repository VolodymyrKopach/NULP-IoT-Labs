import './App.scss';

import React, {Component} from 'react';
import Paper from '@material-ui/core/Paper';
import VacancyTable from "./components/VacancyTable/Vacancies";

class App extends Component {
  render() {
    return (
      <div className='main-container'>
        <Paper className='root'>
          <VacancyTable/>
        </Paper>
      </div>
    );
  }
}

export default App;