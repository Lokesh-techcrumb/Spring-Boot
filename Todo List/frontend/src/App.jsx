import { useEffect, useState } from "react";
import axios from "axios";
import Select from "react-select";
// import Navbar from "./components/Navbar";
import { MdEditNote } from "react-icons/md";
import { AiFillDelete } from "react-icons/ai";
import {
  FaArrowCircleLeft,
  FaArrowCircleRight,
  FaSearch,
} from "react-icons/fa";

const API_BASE = "http://localhost:8080";

function App() {
  const [todos, setTodos] = useState([]);
  const [tags, setTags] = useState([]);
  const [form, setForm] = useState({
    description: "",
    status: "Pending",
    tag: "",
  });
  const [newTag, setNewTag] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [sortBy, setSortBy] = useState("createdAt");
  const [order, setOrder] = useState("desc");
  const [totalPages, setTotalPages] = useState(1);
  const [selectedStatus, setSelectedStatus] = useState("Pending");
  const [selectedTag, setSelectedTag] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");

  // useEffect(() => {
  //   fetchTodos();
  //   fetchTags();
  // }, [page, size, sortBy, order]);

  // const fetchTodos = async () => {
  //   try {
  //     const response = await axios.get(`${API_BASE}/todos`, {
  //       params: { page, size, sortBy, order },
  //     });
  //     setTodos(response.data.content);
  //     setTotalPages(response.data.totalPages);
  //   } catch (error) {
  //     console.error("Error fetching todos:", error);
  //   }
  // };

  // const fetchTags = async () => {
  //   try {
  //     const response = await axios.get(`${API_BASE}/tags`);
  //     setTags(response.data);
  //   } catch (error) {
  //     console.error("Error fetching tags:", error);
  //   }
  // };

  useEffect(() => {
    
    fetchTodos();
    fetchTags();
  // Wait for 500ms to reduce API calls while typing
  }, [page, size, sortBy, order, selectedStatus, selectedTag, searchQuery]);

  const fetchTodos = async () => {

    if(searchQuery.trim()){
      try {
        const response = await fetch(`http://localhost:8080/todos/search?query=${searchQuery}&page=0&size=6`);
        const data = await response.json();
        setTodos([...data.content]); // Make sure todos are updated
      } catch (error) {
        console.error("Error fetching search results", error);
      }
    }
    else{
      try {
        const response = await axios.get(`${API_BASE}/todos`, {
          params: {
            page,
            size,
            sortBy,
            order,
            status: selectedStatus,
            tag: selectedTag,
          },
        });
        setTodos(response.data.content);
        setTotalPages(response.data.totalPages);
      } catch (error) {
        console.error("Error fetching todos:", error);
      }
    }
  };

  const fetchTags = async () => {
    try {
      const response = await axios.get(`${API_BASE}/tags`);
      setTags(response.data);
    } catch (error) {
      console.error("Error fetching tags:", error);
    }
  };

  const handleInputChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleTagChange = (selected) => {
    setForm({ ...form, tag: selected });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = {
      description: form.description,
      status: form.status,
      tag: { name: form.tag.label },
    };

    try {
      if (editingId) {
        await axios.put(`${API_BASE}/todos/${editingId}`, data);
        setEditingId(null);
      } else {
        await axios.post(`${API_BASE}/todos`, data);
      }
      fetchTodos();
      setForm({ description: "", status: "Pending", tag: "" });
    } catch (error) {
      console.error("Error saving todo:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${API_BASE}/todos/${id}`);
      fetchTodos();
    } catch (error) {
      console.error("Error deleting todo:", error);
    }
  };

  const handleEdit = (todo) => {
    setEditingId(todo.id);
    setForm({
      description: todo.description,
      status: todo.status,
      tag: { label: todo.tag.name, value: todo.tag.id },
    });
  };

  const handleStatusToggle = async (id, currentStatus) => {
    try {
      await axios.put(`${API_BASE}/todos/${id}`, {
        ...todos.find((todo) => todo.id === id),
        status: currentStatus === "Pending" ? "Completed" : "Pending",
      });
      fetchTodos();
    } catch (error) {
      console.error("Error updating status:", error);
    }
  };

  const handleAddTag = async () => {
    if (!newTag.trim()) return;
    try {
      await axios.post(`${API_BASE}/tags`, { name: newTag });
      fetchTags();
      setNewTag("");
    } catch (error) {
      console.error("Error adding tag:", error);
    }
  };


  return (
    <div className="min-h-screen bg-gray-100 flex justify-center items-center">
      <div className="container mx-3 max-w-[80vw] md:mx-auto my-5 rounded-xl p-5 bg-white p-6 min-h-[95vh] md:w-[40%] shadow-lg relative">
        <h2 className="text-4xl font-bold text-center text-blue-600 mb-4">
          Todo App
        </h2>
        <h4 className="text-2xl font-bold text-center text-slate-900 mb-4">
          Manage your todos at one place
        </h4>

        <div className="mb-2">

          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder="Search by description, status, or tag..."
            className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <div className="flex space-x-2 justify-between mb-4">
          <input
            type="text"
            placeholder="Search by Tag"
            value={selectedTag}
            onChange={(e) => setSelectedTag(e.target.value === "" ? null : e.target.value)}
            className="border p-2 rounded w-[60%]"
          />

          <select
            value={selectedStatus}
            onChange={(e) => setSelectedStatus(e.target.value)}
            className="border p-2 rounded w-[30%] "
          >
            <option value="Pending">Pending</option>
            <option value="Completed">Completed</option>
          </select>

          <button
            onClick={fetchTodos}
            className="bg-blue-500 text-white px-4 py-2 rounded"
          >
            Search
          </button>
        </div>

        <div className="h-[1px] bg-black opacity-15 w-full mx-auto my-2"></div>

        <form onSubmit={handleSubmit} className="mb-4 space-y-3">
          <input
            type="text"
            name="description"
            value={form.description}
            onChange={handleInputChange}
            placeholder="Todo Description"
            x
            className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500"
            required
          />

          <Select
            options={tags.map((tag) => ({ value: tag.id, label: tag.name }))}
            value={form.tag}
            onChange={handleTagChange}
            placeholder="Select Tag"
            className="w-full"
          />

          <button
            type="submit"
            className="w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600"
          >
            {editingId ? "Update Todo" : "Add Todo"}
          </button>
        </form>

        {/* Add new tag feature */}
        <div className="flex space-x-2 mb-4">
          <input
            type="text"
            value={newTag}
            onChange={(e) => setNewTag(e.target.value)}
            placeholder="New Tag Name"
            className="flex-1 p-2 border border-gray-300 rounded-md"
          />
          <button
            onClick={handleAddTag}
            className="bg-green-500 text-white p-2 rounded-md hover:bg-green-600"
          >
            Add Tag
          </button>
        </div>

        <div className="h-[1px] bg-black opacity-15 w-full mx-auto my-2"></div>
        <h2 className="text-lg text-center font-bold">Your Todos</h2>

        {/* List of Todos */}
        <div className="space-y-2">
          {todos.length === 0 && (
            <div className="m-5 font-bold text-center mt-20">
              No Todos to display
            </div>
          )}
          {todos.map((todo) => (
            <div
              key={todo.id}
              className="flex justify-between items-center bg-gray-200 p-3 rounded-md"
            >
              <div className="flex items-center space-x-3">
                <input
                  type="checkbox"
                  checked={todo.status === "Completed"}
                  onChange={() => handleStatusToggle(todo.id, todo.status)}
                  className="w-5 h-5 accent-blue-500"
                />
                <div>
                  <h3
                    className={`text-lg font-semibold inline-block mr-5 ${
                      todo.status === "Completed"
                        ? "line-through text-gray-500"
                        : ""
                    }`}
                  >
                    {todo.description}
                  </h3>
                  <span className="bg-green-100 text-green-800 text-xs font-medium px-2.5 py-0.5 rounded-full ml-2">
                    {todo.tag?.name}
                  </span>
                </div>
              </div>
              <div className="space-x-2">
                <button
                  onClick={() => handleEdit(todo)}
                  className="px-3 py-1 bg-green-600 text-white rounded-md"
                >
                  <MdEditNote />
                </button>
                <button
                  onClick={() => handleDelete(todo.id)}
                  className="px-3 py-1 bg-red-500 text-white rounded-md"
                >
                  <AiFillDelete />
                </button>
              </div>
            </div>
          ))}
        </div>
        <div className="flex justify-center absolute left-[40%] bottom-4 gap-4 mt-4">
          <button
            onClick={() => setPage(page - 1)}
            disabled={page === 0}
            className="bg-gray-300 p-2 rounded-md"
          >
            <FaArrowCircleLeft />
          </button>
          <span>
            Page {page + 1} of {totalPages}
          </span>
          <button
            onClick={() => setPage(page + 1)}
            disabled={page >= totalPages - 1}
            className="bg-gray-300 p-2 rounded-md"
          >
            <FaArrowCircleRight />
          </button>
        </div>
      </div>
    </div>
  );
}

export default App;