
import React from "react";

function VideosItem({ video }) {
  return (
    <div className="video-card">
      <iframe
        src={`https://www.youtube.com/embed/${video.id.videoId}`}
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
        title={video.snippet.title}
      ></iframe>
      <h3>{video.snippet.title}</h3>
    </div>
  );
}

export default VideosItem;
