const faker = require('faker');
const fs = require('fs');

const writeDataToCsv = (fileName, maxDataRowsNumber) => {
  let data = '';
  const firstDivider = '##';
  const secondDivider = '###';
  const salaries = [300, 400, 500, 600, 700, 800, 900, 1000, 1500, 2000];
  const results = ['successfully', 'not successfully'];
  for (let i = 0; i < maxDataRowsNumber / 3; i++) {
    data += `${i + 1},${faker.name.jobTitle()},${faker.name.jobType()},${salaries[Math.floor(Math.random() * salaries.length)]}\n`
  }
  data += `${firstDivider}\n`;
  for (let i = 0; i < maxDataRowsNumber / 3; i++) {
    data += `${i + 1},${faker.name.lastName()},${faker.name.firstName()},${faker.name.jobTitle()},${Math.floor(Math.random() * maxDataRowsNumber / 3)}\n`
  }
  data += `${secondDivider}\n`;
  for (let i = 0; i < maxDataRowsNumber / 3; i++) {
    data += `${i + 1},${results[Math.floor(Math.random() * results.length)]},${Math.floor(Math.random() * maxDataRowsNumber / 3)}\n`
  }

  fs.writeFile(fileName, data, (err) => {
      if (err) return console.log(err);
    }
  );
};

const maxDataRowsNumber = 3;
writeDataToCsv(fileName, maxDataRowsNumber);



