import './VacancyTableItem.scss';

import React from 'react';
import {TableCell, TableRow, IconButton, Icon} from '@material-ui/core';

const VacancyTableItem = ({vacancy, handleUpdateVacancyDialogOpen, handleDeleteVacancy}) => (
  <TableRow className='vacancy-table-item' key={vacancy.vacancyId}>
    <TableCell className='vacancy-table-item-row' component="th" scope="row">{vacancy.vacancyId}</TableCell>
    <TableCell>{vacancy.vacancyName}</TableCell>
    <TableCell>{vacancy.vacancyDescription}</TableCell>
    <TableCell>{vacancy.vacancySalary}</TableCell>
    <TableCell>
        <IconButton className='action-button' onClick={() => handleUpdateVacancyDialogOpen(vacancy)}>
            <Icon>edit</Icon>
        </IconButton>
        <IconButton className='action-button' onClick={() => handleDeleteVacancy(vacancy.vacancyId)}>
            <Icon>delete</Icon>
        </IconButton>
    </TableCell>
  </TableRow>
);

export default VacancyTableItem;