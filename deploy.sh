#!/bin/bash

set -e

echo "========================================="
echo "🚀 Deploy Tech Challenge - Fase 3"
echo "========================================="

# Garante execução a partir da raiz
PROJECT_ROOT=$(pwd)
echo "📁 Diretório do projeto: $PROJECT_ROOT"

echo ""
echo "🧹 Limpando builds antigos..."
./mvnw clean package -DskipTests

echo ""
echo "🐳 Subindo containers com Docker Compose..."
docker-compose down
docker-compose build
docker-compose up -d

echo ""
echo "⏳ Aguardando containers iniciarem..."
sleep 5

echo ""
echo "📦 Status dos containers:"
docker-compose ps

echo ""
echo "✅ Deploy finalizado com sucesso!"
echo ""
echo "🌐 Acessos:"
echo " - Agendamento Service: http://localhost:8080"
echo " - Notificação Service: http://localhost:8081"
echo " - RabbitMQ UI: http://localhost:15672 (guest / guest)"
echo "========================================="
