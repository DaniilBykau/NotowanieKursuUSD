

export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}

export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}
//pobrano z https://git.pg.edu.pl/internet-services-architectures/simple-rpg/tree/javascript


