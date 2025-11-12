import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import "../CSS/auth.css";

function Profile() {
  const { user, isAuthenticated, logout, updateProfile, deleteAccount } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    username: "",
    email: "",
    teamName: "",
    password: "",
    confirmPassword: "",
  });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
      return;
    }
    if (user) {
      setForm((prev) => ({
        ...prev,
        username: user.username || "",
        email: user.email || "",
        teamName: user.teamName || "",
      }));
    }
  }, [isAuthenticated, user, navigate]);

  const onChange = (e) => {
    const { id, value } = e.target;
    setForm((prev) => ({ ...prev, [id]: value }));
  };

  const handleSave = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    // Basic validation
    if (!form.username || !form.email || !form.teamName) {
      setError("Username, Email and Team Name are required.");
      return;
    }
    if (form.password || form.confirmPassword) {
      if (form.password !== form.confirmPassword) {
        setError("Passwords do not match");
        return;
      }
      if (form.password.length < 6) {
        setError("New password must be at least 6 characters");
        return;
      }
    }

    setLoading(true);
    const payload = {
      username: form.username,
      email: form.email,
      teamName: form.teamName,
      ...(form.password ? { password: form.password } : {}),
    };

    const result = await updateProfile(user.id, payload);
    if (result.success) {
      setMessage("Profile updated successfully");
      setForm((prev) => ({ ...prev, password: "", confirmPassword: "" }));
    } else {
      setError(result.message || "Failed to update profile");
    }
    setLoading(false);
  };

  const handleDelete = async () => {
    const confirm = window.confirm("Are you sure you want to delete your account? This cannot be undone.");
    if (!confirm) return;

    setLoading(true);
    const result = await deleteAccount(user.id);
    setLoading(false);

    if (result.success) {
      navigate("/");
    } else {
      setError(result.message || "Failed to delete account");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Your Profile</h2>
        <form onSubmit={handleSave}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input id="username" type="text" value={form.username} onChange={onChange} disabled={loading} />
          </div>

          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input id="email" type="email" value={form.email} onChange={onChange} disabled={loading} />
          </div>

          <div className="form-group">
            <label htmlFor="teamName">Team Name</label>
            <input id="teamName" type="text" value={form.teamName} onChange={onChange} disabled={loading} />
          </div>

          <hr style={{ margin: "20px 0", opacity: 0.2 }} />
          <p style={{ marginTop: 0, color: "#666" }}>Change password (optional)</p>

          <div className="form-group">
            <label htmlFor="password">New Password</label>
            <input id="password" type="password" value={form.password} onChange={onChange} disabled={loading} />
          </div>

          <div className="form-group">
            <label htmlFor="confirmPassword">Confirm New Password</label>
            <input id="confirmPassword" type="password" value={form.confirmPassword} onChange={onChange} disabled={loading} />
          </div>

          {error && <div className="error-message">{error}</div>}
          {message && (
            <div style={{ background: "#e8f5e9", color: "#2e7d32", padding: 12, borderRadius: 6, marginBottom: 20, border: "1px solid #c8e6c9", textAlign: "center", fontSize: 14 }}>
              {message}
            </div>
          )}

          <button type="submit" className="auth-button" disabled={loading}>
            {loading ? "Saving..." : "Save Changes"}
          </button>
        </form>

        <div className="auth-footer" style={{ marginTop: 16 }}>
          <button onClick={handleDelete} className="auth-button" style={{ background: "grey" }} disabled={loading}>
            {loading ? "Processing..." : "Delete Account"}
          </button>
        </div>
      </div>
    </div>
  );
}

export default Profile;
