let subjects = [
    { name: "Mathematics", marks: 0 },
    { name: "Science", marks: 0 },
    { name: "English", marks: 0 },
    { name: "History", marks: 0 },
    { name: "Computer Science", marks: 0 }
];


let container = document.getElementById("subjectsContainer");
subjects.forEach((subject, index) => {
    container.innerHTML += `
        <div class="mb-3">
            <label class="form-label">${subject.name} Marks</label>
            <input type="number" class="form-control" id="marks-${index}" placeholder="Enter marks (0-100)" min="0" max="100" required>
        </div>
    `;
});

document.getElementById("reportForm").addEventListener("submit", function (event) {
    event.preventDefault();

    let totalMarks = 0;
    let maxMarks = subjects.length * 100;

    subjects.forEach((subject, index) => {
        let marks = parseInt(document.getElementById(`marks-${index}`).value) || 0;
        subject.marks = marks;
        totalMarks += marks;
    });

    let percentage = (totalMarks / maxMarks) * 100;
    let grade = "";

    if (percentage >= 90) grade = "A+";
    else if (percentage >= 80) grade = "A";
    else if (percentage >= 70) grade = "B";
    else if (percentage >= 60) grade = "C";
    else if (percentage >= 50) grade = "D";
    else grade = "F";

   
    let report = {
        subjects: subjects,
        totalMarks: totalMarks,
        percentage: percentage.toFixed(2) + "%",
        grade: grade
    };

    alert("Report Card:\n" + JSON.stringify(report, null, 2));
});