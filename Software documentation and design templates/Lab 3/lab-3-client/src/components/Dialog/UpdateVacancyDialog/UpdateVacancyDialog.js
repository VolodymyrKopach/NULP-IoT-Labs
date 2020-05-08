import './UpdateVacancyDialog.scss';

import React, {Component} from 'react';
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import Button from "@material-ui/core/Button";
import DialogTitle from "@material-ui/core/DialogTitle";
import Dialog from "@material-ui/core/Dialog";

class UpdateVacancyDialog extends Component {

  constructor(props) {
    super(props);
    this.state = {
      data: {
        id: 0,
        name: '',
        description: '',
        salary: 0
      }
    };
  }

  componentDidMount() {
    const {vacancy} = this.props;
    this.setState({
      data: {
        id: vacancy.vacancyId,
        name: vacancy.vacancyName,
        description: vacancy.vacancyDescription,
        salary: vacancy.vacancySalary
      }
    })
  }

  handleUpdateVacancy(handleUpdateVacancy, handleClose) {
    handleUpdateVacancy(this.state.data);
    handleClose();
  };

  render() {
    const {handleClose, handleUpdateVacancy} = this.props;
    const {data} = this.state;
    return (
      <Dialog className='dialog' onClose={handleClose} aria-labelledby="customized-dialog-title" open={true}>
        <DialogTitle id="customized-dialog-title" onClose={handleClose}>
          Update vacancy
        </DialogTitle>

        <DialogContent dividers>
          <div className='input-fields-container'>
            <input
              className='input-field form-control'
              type="text"
              value={data.name}
              onChange={(e) => this.setState({data: {...this.state.data, name: e.target.value}})}
              placeholder="Name"
              style={{width: '200px'}}/>

            <input
              className='input-field form-control'
              type="text"
              value={data.description}
              onChange={(e) => this.setState({data: {...this.state.data, description: e.target.value}})}
              placeholder="Description"
              style={{width: '200px'}}/>

            <input
              className='input-field form-control'
              type="text"
              value={data.salary}
              onChange={(e) => this.setState({data: {...this.state.data, salary: e.target.value}})}
              placeholder="Salary"
              style={{width: '200px'}}/>
          </div>
        </DialogContent>

        <DialogActions>
          <Button autoFocus onClick={() => this.handleUpdateVacancy(handleUpdateVacancy, handleClose)} color="primary">
            Update
          </Button>
        </DialogActions>
      </Dialog>)
  }
}

export default UpdateVacancyDialog;