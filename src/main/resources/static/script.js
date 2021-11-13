import {getBackendUrl} from '../js/configuration.js';
import {createTextCell, clearElementChildren} from '../js/dom_utils.js';

let bidsArray = null;
let datesArrayString = null;
let asksArray = null;
let ask_diff = null;
let bid_diff = null;

function fetchAskDiff(x) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            calculateAsks(this.response)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/usd/asks/difference/' + x, true);
    xhttp.send();
}

function fetchBidDiff(x) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            calculateBids(this.response)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/usd/bids/difference/' + x, true);
    xhttp.send();
}

function fetchAsks(x) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadAsks(this.response)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/usd/asks/' + x, true);
    xhttp.send();
}

function fetchDates(x) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadDates(this.response)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/usd/dates/' + x, true);
    xhttp.send();
}

function fetchBids(x) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadBids(this.response)
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/usd/bids/' + x, true);
    xhttp.send();

}

function loadBids(response_asks) {
    let bids = response_asks
    bids = bids.replace("[", "").replace("]", "")
    let bidsArrayString = bids.split(',')
    bidsArray = bidsArrayString.map(num => Number(num));
}

function loadAsks(response_asks) {
    let asks = response_asks
    asks = asks.replace("[", "").replace("]", "")
    let asksArrayString = asks.split(',')
    asksArray = asksArrayString.map(num => Number(num));
}

function loadDates(response_asks) {
    let dates = response_asks
    dates = dates.replace("[", "").replace("]", "")
    datesArrayString = dates.split(',')

}

function buildTable() {

    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    tableBody.appendChild(createTableRowHeader())
    for (let i = 0; i < bidsArray.length; i++) {
        tableBody.appendChild(createTableRow(i));
    }

}

function createTableRowHeader() {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell("Data"));
    tr.appendChild(createTextCell("Skup"));
    tr.appendChild(createTextCell("Sprzedaż"));
    return tr;
}

function createTableRow(i) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(datesArrayString[i]));
    tr.appendChild(createTextCell(bidsArray[i].toString()));
    tr.appendChild(createTextCell(asksArray[i]));
    return tr;
}

function calculateAsks(data) {
    let pAsk = document.getElementById('diff_bid').innerHTML = "Różnica sprzedaży " + data;
}

function calculateBids(data) {
    let pAsk = document.getElementById('diff_ask').innerHTML = "Różnica kupna " + data;
}

function loadDiagram() {
    const labels = datesArrayString;
    const data = {
        labels: labels,
        datasets: [{
            label: 'Skup',
            data: bidsArray,
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }, {
            label: 'Sprzedaż',
            data: asksArray,
            fill: false,
            borderColor: 'rgb(0, 0, 192)',
            tension: 0.1
        }]
    };
    const ctx = document.getElementById('myChart').getContext('2d');
    const stackedLine = new Chart(ctx, {
        type: 'line',
        data: data,
        options: {
            scales: {
                y: {
                    stacked: true
                }
            }
        }
    });
}

function start(x) {
    fetchAsks(x);  //pobranie historii kursu sprzedaży
    fetchBids(x);   //pobranie historii kursu kupna
    fetchDates(x);  //pobranie dat

    setTimeout(() => {
        buildTable();   //budowanie tabeli
        loadDiagram()   //rysowanie
        fetchAskDiff(x);    //różnica sprzedaży
        fetchBidDiff(x)     //różnica kupna
    }, 1000);
}

document.getElementById("start").addEventListener('click', () => {
    let x = document.getElementById("date").value;
    start(x)
});


