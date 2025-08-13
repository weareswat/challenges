import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  upvote(event) {
    event.preventDefault()
    const postId = event.currentTarget.dataset.postId
    this.vote(postId, 'upvote')
  }

  downvote(event) {
    event.preventDefault()
    const postId = event.currentTarget.dataset.postId
    this.vote(postId, 'downvote')
  }

  vote(postId, action) {
    const button = event.currentTarget
    const originalText = button.textContent
    
    // Disable button and show loading state
    button.disabled = true
    button.textContent = '...'

    fetch(`/api/v1/posts/${postId}/${action}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-CSRF-Token': document.querySelector('meta[name="csrf-token"]')?.getAttribute('content') || ''
      }
    })
    .then(response => {
      if (response.ok) {
        return response.json().then(data => {
          window.location.reload()
        })
      } else {
        // Parse the error response to get the actual error message
        return response.json().then(errorData => {
          const errorMessage = errorData.error || 'An error occurred while processing your request.'
          throw new Error(errorMessage)
        }).catch(() => {
          // If JSON parsing fails, use a generic message based on status code
          const statusMessages = {
            404: 'Post not found. It may have been deleted.',
            409: 'Post was updated by another user. Please try again.',
            422: 'Invalid request. Please check your input.',
            500: 'Server error. Please try again later.'
          }
          const message = statusMessages[response.status] || `Server error (${response.status}). Please try again.`
          throw new Error(message)
        })
      }
    })
        .catch(error => {
      this.showErrorMessage(error.message)
      button.textContent = originalText
      button.disabled = false
    })
  }

  showErrorMessage(message) {
    // Create or find error message element
    let errorElement = document.querySelector('.error-message')
    if (!errorElement) {
      errorElement = document.createElement('div')
      errorElement.className = 'error-message'
      this.element.appendChild(errorElement)
    }
    
    errorElement.textContent = message
    errorElement.style.display = 'block'
    
    // Auto-hide error message after 5 seconds
    setTimeout(() => {
      errorElement.style.display = 'none'
    }, 5000)
  }
}
