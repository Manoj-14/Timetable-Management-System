const { json } = require("body-parser");
const express = require("express");
const { Collection } = require("mongodb");
const { Schema } = require("mongoose");
const mongoClient = require("mongodb").MongoClient;
const mongoose = require("mongoose");
const app = express();
const url =
  "mongodb+srv://admin:admin123@sandbox.ubahq.mongodb.net/?retryWrites=true&w=majority";
const port = process.env.PORT || 9001;
app.set("port", port);
app.use(express.json());

mongoose
  .connect(url, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => {
    console.log("Connected to MongoDB");
    app.listen(port, () => {
      console.log(`Server is running on port ${port}`);
    });
  })
  .catch((err) => {
    console.log("Error connecting to MongoDB: ", err.message);
  });

const Admin = new Schema({
  name: String,
  email: String,
  password: String,
});

const studentSch = new Schema({
  name: {
    type: String,
    required: true,
  },
  USN: {
    type: String,
    required: true,
  },
  sem: {
    type: Number,
    required: true,
    max: 8,
    min: 1,
  },
  branch: {
    type: String,
    enum: ["CSE", "ECE", "EEE", "ISE", "CIV", "MEC"],
    required: true,
  },
  password: {
    type: String,
    required: true,
  },
});

const Faculty = new Schema({
  name: {
    type: String,
    required: true,
  },
  Fid: {
    type: String,
    required: true,
  },
  branch: {
    type: String,
    required: true,
    enum: ["CSE", "ECE", "EEE", "ISE", "CIV", "MEC"],
  },
  password: {
    type: String,
    required: true,
  },
  sem: {
    type: Array,
    required: true,
    max: 8,
    min: 1,
  },
});

const Timetable = new Schema({
  fid: {
    type: String,
    required: true,
  },
  sem: {
    type: Number,
    required: true,
    max: 8,
    min: 1,
  },
  subCode: {
    type: String,
    required: true,
  },
  subName: {
    type: String,
    required: true,
  },
  branch: {
    type: String,
    required: true,
    enum: ["CSE", "ECE", "EEE", "ISE", "CIV", "MEC"],
  },

  monday: {
    type: String,
    required: true,
  },
  tuesday: {
    type: String,
    required: true,
  },
  wednesday: {
    type: String,
    required: true,
  },
  thursday: {
    type: String,
    required: true,
  },
  friday: {
    type: String,
    required: true,
  },
  saturday: {
    type: String,
    required: true,
  },
});

const Student = mongoose.model("Student", studentSch);
const admin = mongoose.model("admin", Admin);
const faculty = mongoose.model("faculty", Faculty);
const timetable = mongoose.model("timetable", Timetable);

const adminData = new admin({
  name: "admin",
  email: "admin@gmainl.com",
  password: "admin123",
});

// adminData.save();

app.post("/login", (req, res) => {
  const query = { USN: req.body.usn.toUpperCase() };
  Student.findOne(query, (err, result) => {
    console.log(result);
    if (err) console.log("Error: " + err);
    else if (result == null) {
      res.status(400).send();
    } else if (result.password != req.body.password) {
      res.status(404).send();
    } else {
      timetable.find(
        { sem: result.sem, branch: result.branch },
        (err, resp) => {
          if (err) console.log("Error: " + err);
          else {
            // res.send();
            var subCodeArr = [],
              subNameArr = [],
              monArr = [],
              tueArr = [],
              wedArr = [],
              thuArr = [],
              friArr = [],
              satArr = [],
              semArr = [];

            for (var i = 0; i < resp.length; i++) {
              semArr.push(resp[i].sem);
              subCodeArr.push(resp[i].subCode);
              subNameArr.push(resp[i].subName);
              monArr.push(resp[i].monday);
              tueArr.push(resp[i].tuesday);
              wedArr.push(resp[i].wednesday);
              thuArr.push(resp[i].thursday);
              friArr.push(resp[i].friday);
              satArr.push(resp[i].saturday);
            }
            console.log(
              semArr,
              subCodeArr,
              subNameArr,
              monArr,
              tueArr,
              wedArr,
              thuArr,
              friArr,
              satArr
            );
            const objToSend = {
              name: result.name,
              usn: result.USN,
              branch: result.branch,
              sem: result.sem,
              ttlSem: semArr,
              subCode: subCodeArr,
              subName: subNameArr,
              mon: monArr,
              tue: tueArr,
              wed: wedArr,
              thr: thuArr,
              fri: friArr,
              sat: satArr,
            };
            res.status(200).send(JSON.stringify(objToSend));
          }
        }
      );
      console.log(result);
    }
  });
});

app.post("/addClsTime", (req, res) => {
  const query = {
    Fid: req.body.fid.toUpperCase(),
    subCode: req.body.subCode.toUpperCase(),
  };
  timetable.findOne(query, (err, result) => {
    console.log(result);
    if (err) console.log("Error: " + err);
    else if (result == null) {
      res.status(400).send();
    } else {
      const objToSend = new timetable({
        Fid: result.Fid,
        sem: result.sem,
        branch: result.branch,
        subCode: result.subCode,
        subName: result.subName,
        monday: req.body.monday,
        tuesday: req.body.tuesday,
        wednesday: req.body.wednesday,
        thursday: req.body.thursday,
        friday: req.body.friday,
        saturday: req.body.saturday,
      })
        .save()
        .then((err, result) => {
          if (err) console.log("Error: " + err);
          else res.status(200).send();
          console.log(result);
        });
    }
  });
});

app.post("/signup", (req, res) => {
  const userData = new Student({
    name: req.body.name,
    USN: req.body.usn,
    sem: req.body.sem,
    branch: req.body.branch,
    password: req.body.usn + "@123",
  });
  const query = { USN: req.body.usn };
  Student.findOne(query, (err, result) => {
    if (err) console.log("Error: " + err);
    else if (result == null) {
      userData.save((err, result) => {
        if (err) console.log("Error: " + err);
        else {
          console.log("User added");
          res.status(200).send();
        }
      });
    } else {
      res.status(400).send();
    }
  });
});

app.post("/facReg", (req, res) => {
  console.log(req.body);
  var sem = [];
  if (req.body.sem1 != undefined) {
    sem.push(req.body.sem1);
  }
  if (req.body.sem2 != undefined) {
    sem.push(req.body.sem2);
  }
  if (req.body.sem3 != undefined) {
    sem.push(req.body.sem3);
  }
  if (req.body.sem4 != undefined) {
    sem.push(req.body.sem4);
  }
  if (req.body.sem5 != undefined) {
    sem.push(req.body.sem5);
  }
  if (req.body.sem6 != undefined) {
    sem.push(req.body.sem6);
  }
  if (req.body.sem7 != undefined) {
    sem.push(req.body.sem7);
  }
  if (req.body.sem8 != undefined) {
    sem.push(req.body.sem8);
  }

  const userData = new faculty({
    name: req.body.name,
    Fid: req.body.id,
    branch: req.body.branch,
    password: req.body.id + "@123",
    sem: sem,
  });
  console.log(userData);

  const query = { Fid: req.body.id };
  faculty.findOne(query, (err, result) => {
    if (err) console.log("Error: " + err);
    else if (result == null) {
      userData.save((err, result) => {
        if (err) console.log("Error: " + err);
        else {
          console.log("User added");
          res.status(200).send();
        }
      });
    } else {
      res.status(400).send();
    }
  });
});

app.post("/facLogin", (req, res) => {
  const query = { Fid: req.body.fid };
  console.log(req.body);
  var dataArr = [];
  faculty.findOne(query, (err, result) => {
    console.log(result);
    if (err) console.log("Error: " + err);
    else if (result == null) {
      res.status(400).send();
    } else if (result.password != req.body.password) {
      res.status(404).send();
    } else {
      timetable.find({ fid: req.body.fid }, (err, resp) => {
        var arrData = [],
          semArr = [],
          subCodeArr = [],
          subNameArr = [],
          monArr = [],
          tueArr = [],
          wedArr = [],
          thuArr = [],
          friArr = [],
          satArr = [];

        for (var i = 0; i < resp.length; i++) {
          semArr.push(resp[i].sem);
          subCodeArr.push(resp[i].subCode);
          subNameArr.push(resp[i].subName);
          monArr.push(resp[i].monday);
          tueArr.push(resp[i].tuesday);
          wedArr.push(resp[i].wednesday);
          thuArr.push(resp[i].thursday);
          friArr.push(resp[i].friday);
          satArr.push(resp[i].saturday);
        }
        console.log(
          semArr,
          subCodeArr,
          subNameArr,
          monArr,
          tueArr,
          wedArr,
          thuArr,
          friArr,
          satArr
        );
        if (err) console.log("Error: " + err);
        else {
          const objToSend = {
            name: result.name,
            fid: result.Fid,
            branch: result.branch,
            sem: result.sem,
            data: arrData,
            ttlSem: semArr,
            subCode: subCodeArr,
            subName: subNameArr,
            mon: monArr,
            tue: tueArr,
            wed: wedArr,
            thr: thuArr,
            fri: friArr,
            sat: satArr,
          };
          res.status(200).send(JSON.stringify(objToSend));
        }
      });
    }
  });
});

app.post("/adminLogin", (req, res) => {
  console.log(req.body);
  const { email, password } = req.body;
  admin.findOne({ email, password }, (err, result) => {
    if (err) console.log("Error: " + err);
    else {
      console.log(result);
      if (result) {
        const objToSend = {
          name: result.name,
          email: result.email,
        };
        res.status(200).send(JSON.stringify(objToSend));
      } else {
        res.status(400).send("Admin not found");
      }
    }
  });
});

app.post("/adminPassUpdate", (req, res) => {
  const query = { email: req.body.email };

  admin.findOne(query, (err, result) => {
    if (err) console.log("Error: " + err);
    else if (result == null) {
      res.status(400).send();
    } else if (result.password != req.body.oldPassword) {
      res.status(404).send();
    } else {
      const updateQuery = { $set: { password: req.body.newPassword } };
      admin.updateOne(query, updateQuery, (err, result) => {
        if (err) console.log("Error: " + err);
        else {
          console.log(result);
          res.status(200).send();
        }
      });
    }
  });
});

app.post("/facAddCls", async (req, res) => {
  const userData = new timetable({
    fid: req.body.fid,
    sem: req.body.sem,
    branch: req.body.branch,
    subCode: req.body.subcode,
    subName: req.body.subname,
    monday: req.body.mon,
    tuesday: req.body.tue,
    wednesday: req.body.wed,
    thursday: req.body.thu,
    friday: req.body.fri,
    saturday: req.body.sat,
  });
  const query = {
    fid: req.body.fid,
    subCode: req.body.subcode,
  };
  timetable.findOne(query, (err, result) => {
    if (err) console.log("Error: " + err);
    else if (result == null) {
      userData.save((err, result) => {
        console.log(result);
        if (err) console.log("Error: " + err);
        else {
          console.log("User added");
          res.status(200).send();
        }
      });
    } else {
      timetable.updateOne(
        query,
        {
          $set: {
            monday: req.body.mon,
            tuesday: req.body.tue,
            wednesday: req.body.wed,
            thursday: req.body.thu,
            friday: req.body.fri,
            saturday: req.body.sat,
          },
        },
        (err, result) => {
          if (err) console.log("Error: " + err);
          else if (result.nModified == 0) {
            res.status(403).send();
          } else {
            console.log("User added");
            res.status(201).send();
          }
        }
      );
    }
  });
});

// write route to update student password in database
app.post("/stuPassUpt", (req, res) => {
  console.log(req.body);
  const query = { USN: req.body.usn };

  Student.findOne(query, (err, result) => {
    if (err) console.log("Error: " + err);
    else if (result == null) {
      res.status(404).send();
    } else if (result.password != req.body.oldPassword) {
      res.status(400).send();
    } else {
      const updateQuery = { $set: { password: req.body.newPassword } };
      Student.updateOne(query, updateQuery, (err, result) => {
        if (err) console.log("Error: " + err);
        else {
          console.log(result);
          res.status(200).send();
        }
      });
    }
  });
});
