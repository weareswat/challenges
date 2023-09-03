module ActionDispatch
  class PublicExceptionsJson < PublicExceptions
    def call(env)
      request = ActionDispatch::Request.new(env)
      status  = request.path_info[1..-1].to_i
      body    = { error: env["action_dispatch.exception"].message }

      render(status, 'json', body)
    end
  end
end
