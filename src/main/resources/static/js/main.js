async function ChangeCity() {
    GetAllCityDescriptions();
    document.getElementById("rename_city").value = document.getElementById("select_city").value
}

function ChangeDescription() {
    document.getElementById("correct_description").value = document.getElementById("select_description").value
}


async function AddCityFunction() {
    let cityObj = { city: document.getElementById("add_city").value }
    let objJson = JSON.stringify(cityObj);
    let url = 'http://localhost:8082/city/add';
    try {
        const response = await fetch(url, {
            method: 'POST',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const json = await response.json();
    } catch (error) {
    }

    document.getElementById("add_city").value = ""

    GetAllCities()


}

function GetAllCities() {
    let url = 'http://localhost:8082/city/get';
    fetch(url, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then((objJson) => {
            let parentElement = document.getElementById('select_city');
            while (parentElement.firstChild) {
                parentElement.removeChild(parentElement.firstChild);
            }
            objJson.forEach(city => {
                let element_option = document.createElement('option')
                element_option.id = city.id;
                element_option.textContent = city.city;
                parentElement.appendChild(element_option);
            })
            console.log(objJson);
        });

    setTimeout(() => {  GetAllCityDescriptions(); }, 200);
}

async function DeleteCity() {
    let cityObj = { city: document.getElementById("select_city").value }
    let objJson = JSON.stringify(cityObj);
    let url = 'http://localhost:8082/city/delete';
    try {
        const response = await fetch(url, {
            method: 'DELETE',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
    }
    GetAllCities()
}

async function RenameCity() {
    let cityObjOld = { city: document.getElementById("select_city").value };
    let cityObjNew = { city: document.getElementById("rename_city").value };
    let cityObjArray = [cityObjOld, cityObjNew];

    let objJson = JSON.stringify(cityObjArray);
    let url = 'http://localhost:8082/city/update';
    try {
        const response = await fetch(url, {
            method: 'PUT',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
    }
    GetAllCities()
}

async function AddCityDescriptionFunction() {
    let city = document.getElementById("select_city").value;
    let descriptionObj = { description: document.getElementById("add_description").value }
    let objJson = JSON.stringify(descriptionObj);
    let url = 'http://localhost:8082/description/add?city=' + city;
    try {
        const response = await fetch(url, {
            method: 'POST',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const json = await response.json();
    } catch (error) {
    }
    document.getElementById("add_description").value = "";

    GetAllCityDescriptions()
}

function GetAllCityDescriptions() {
    let city = document.getElementById("select_city").value;
    let url = 'http://localhost:8082/description/get?city=' + city;
    fetch(url, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then((objJson) => {
            let parentElement = document.getElementById('select_description');
            while (parentElement.firstChild) {
                parentElement.removeChild(parentElement.firstChild);
            }
            objJson.forEach(description => {
                let element_option = document.createElement('option')
                element_option.id = description.id;
                element_option.textContent = description.description;
                parentElement.appendChild(element_option);
            })
            console.log(objJson);
        });
}


async function DeleteDescription() {
    let city = document.getElementById("select_city").value;
    let descriptionObj = { description: document.getElementById("select_description").value }
    let objJson = JSON.stringify(descriptionObj);
    let url = 'http://localhost:8082/description/delete?city=' + city;
    try {
        const response = await fetch(url, {
            method: 'DELETE',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
    }

    GetAllCityDescriptions()
}


async function CorrectDescription(){
    let city = document.getElementById("select_city").value;
    let descriptionObjOld = { description: document.getElementById("select_description").value };
    let descriptionObjNew = { description: document.getElementById("correct_description").value };
    let cityObjArray = [descriptionObjOld, descriptionObjNew];

    let objJson = JSON.stringify(cityObjArray);
    let url = 'http://localhost:8082/description/update?city=' + city;
    try {
        const response = await fetch(url, {
            method: 'PUT',
            body: objJson,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    } catch (error) {
    }

    GetAllCityDescriptions()
}