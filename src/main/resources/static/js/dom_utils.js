

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



