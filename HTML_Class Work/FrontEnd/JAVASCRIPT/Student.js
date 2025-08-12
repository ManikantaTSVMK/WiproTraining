const subjects = [
    { name: "Maths" },
    { name: "Science" },
    { name: "English" },
    { name: "Social Studies" },
    { name: "Computer" }
];


const subjectsContainer = document.getElementById("subjectsContainer");
subjects.forEach((subject, idx) => {
    subjectsContainer.innerHTML += `
        <div class="mb-3">
            <label for="subject${idx}" class="form-label">${subject.name} Marks:</label>
            <input type="number" min="0" max="100" class="form-control" id="subject${idx}" required>
        </div>
    `;
});

document.getElementById("reportForm").addEventListener("submit", function(e) {
    e.preventDefault();

    let total = 0;
    let marks = [];
    let resultHtml = `<h3 class="mt-4">Report Card</h3><table class="table table-bordered"><thead><tr><th>Subject</th><th>Marks</th></tr></thead><tbody>`;

    subjects.forEach((subject, idx) => {
        const mark = parseInt(document.getElementById(`subject${idx}`).value) || 0;
        marks.push(mark);
        total += mark;
        resultHtml += `<tr><td>${subject.name}</td><td>${mark}</td></tr>`;
    });

    resultHtml += `</tbody></table>`;

    const average = (total / subjects.length).toFixed(2);
    let grade = "";
    if (average >= 90) grade = "A+";
    else if (average >= 80) grade = "A";
    else if (average >= 70) grade = "B";
    else if (average >= 60) grade = "C";
    else if (average >= 50) grade = "D";
    else grade = "F";

    resultHtml += `<p><strong>Total Marks:</strong> ${total}</p>`;
    resultHtml += `<p><strong>Average:</strong> ${average}</p>`;
    resultHtml += `<p><strong>Grade:</strong> ${grade}</p>`;

    subjectsContainer.innerHTML = resultHtml;
});